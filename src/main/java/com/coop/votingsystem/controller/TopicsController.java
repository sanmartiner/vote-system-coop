package com.coop.votingsystem.controller;

import com.coop.votingsystem.dto.response.TopicResponseDTO;
import com.coop.votingsystem.model.entitiy.Topics;
import com.coop.votingsystem.model.entitiy.TopicsEntityId;
import com.coop.votingsystem.model.interfaces.TopicsService;
import com.coop.votingsystem.model.requestmodel.TopicRequestModel;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("api/v1/votingSystem/topics")
public class TopicsController {
    private final TopicsService service;
    private final ModelMapper modelMapper;
    @Autowired
    public TopicsController(TopicsService service, ModelMapper modelMapper){
        this.service = service;
        this.modelMapper = modelMapper;
    }


    @ApiOperation(value = "Create a topic register to assembly elections", response = TopicResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created the resource"),
            @ApiResponse(code = 400, message = "Cannot process the request due to a client error"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = HttpHeaders.ACCEPT_LANGUAGE, value = "Accept Language", required = true,
                    paramType = "header", dataTypeClass = String.class, example = "en-US"),
    })

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Object> topicsRegister(@RequestBody final TopicRequestModel topicRequestModel) {
            Long id = service.topicsRegister(Topics.builder()
                    .description(topicRequestModel.getDescription())
                    .topicsEntityId(TopicsEntityId.builder()
                            .title(topicRequestModel.getTitle())
                            .build())
                    .votingDate(topicRequestModel.getVotingDate())
                    .build());
            return ResponseEntity.ok(TopicResponseDTO.builder()
                            .id(id)
                            .title(topicRequestModel.getTitle())
                            .description(topicRequestModel.getDescription())
                            .votingDate(topicRequestModel.getVotingDate()).build());
        }

    @ApiOperation(value = "Get a register of a specific Topic", response = TopicResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created the resource"),
            @ApiResponse(code = 400, message = "Cannot process the request due to a client error"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = HttpHeaders.ACCEPT_LANGUAGE, value = "Accept Language", required = true,
                    paramType = "header", dataTypeClass = String.class, example = "en-US"),

    })

    @GetMapping(value = "/getOne/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getOne(@PathVariable Long id) {
        Topics t = service.getById(id);

        if (Objects.isNull(t)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(TopicResponseDTO.builder()
                .id(t.getTopicsEntityId().getId())
                .votingDate(t.getVotingDate())
                .title(t.getTopicsEntityId().getTitle())
                .description(t.getDescription())
                .build());
    }

    @ApiOperation(value = "List all topics with pagination", response = TopicResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created the resource"),
            @ApiResponse(code = 400, message = "Cannot process the request due to a client error"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = HttpHeaders.ACCEPT_LANGUAGE, value = "Accept Language", required = true,
                    paramType = "header", dataTypeClass = String.class, example = "en-US"),
    })


    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAll(
            @RequestParam int size,
            @RequestParam int page,
            @RequestParam(required = false, defaultValue = "asc") String direction,
            @RequestParam(required = false, defaultValue = "topicsEntityId.id") String sortField) {

        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortField);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Topics> response = service.getAll(pageable);

        return ResponseEntity.ok(response);
    }


}

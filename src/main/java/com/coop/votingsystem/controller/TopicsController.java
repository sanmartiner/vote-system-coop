package com.coop.votingsystem.controller;

import com.coop.votingsystem.dto.response.TopicResponseDTO;
import com.coop.votingsystem.model.entitiy.Topics;
import com.coop.votingsystem.model.entitiy.TopicsEntityId;
import com.coop.votingsystem.model.interfaces.TopicsService;
import io.swagger.annotations.*;
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

import java.util.Collections;
import java.util.Date;

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
        public ResponseEntity<TopicResponseDTO> topicsRegister(@RequestParam String title, String description, Date votingDate) {
            Long id = service.topicsRegister(Topics.builder()
                    .description(description)
                    .id(TopicsEntityId.builder()
                            .title(title)
                            .votingDate(votingDate)
                            .build())
                    .build());
            return ResponseEntity.ok(TopicResponseDTO.builder()
                            .id(id)
                            .title(title)
                            .description(description)
                            .votingDate(votingDate).build());
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
    public ResponseEntity<TopicResponseDTO> getOne(@PathVariable long id) {
        TopicResponseDTO topic = modelMapper.map(service.getById(id), TopicResponseDTO.class);
        return ResponseEntity.ok(topic);
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
    public ResponseEntity<Page<TopicResponseDTO>> getAll(
            @RequestParam int size,
            @RequestParam int page,
            @RequestParam(required = false, defaultValue = "asc") String direction,
            @RequestParam(required = false, defaultValue = "id") String sortField) {

        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortField);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<TopicResponseDTO> response = service.getAll(pageable).map(topic -> modelMapper.map(topic, TopicResponseDTO.class));

        return ResponseEntity.ok(response);
    }


}

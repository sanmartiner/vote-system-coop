package com.coop.votingsystem.controller;

import com.coop.votingsystem.dto.response.ResultResponseDTO;
import com.coop.votingsystem.dto.response.TopicResponseDTO;
import com.coop.votingsystem.model.entitiy.Topics;
import com.coop.votingsystem.model.entitiy.TopicsEntityId;
import com.coop.votingsystem.model.interfaces.TopicsService;
import io.swagger.annotations.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
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

import java.util.Date;

@RestController
@RequestMapping("api/v1/votingSystem/result")
public class ResultController {
    private final TopicsService service;
    private final ModelMapper modelMapper;
    @Autowired
    public ResultController(TopicsService service, ModelMapper modelMapper){
        this.service = service;
        this.modelMapper = modelMapper;
    }



    @ApiOperation(value = "Get a voting result by a topic ID", response = ResultResponseDTO.class)
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

    @GetMapping(value = "/{topicId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TopicResponseDTO> getOne(@PathVariable long id) {
        TopicResponseDTO topic = modelMapper.map(service.getById(id), TopicResponseDTO.class);
        return ResponseEntity.ok(topic);
    }



}

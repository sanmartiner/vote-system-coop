package com.coop.votingsystem.controller;

import com.coop.votingsystem.dto.response.ResultResponseDTO;

import com.coop.votingsystem.model.interfaces.ResultService;
import io.swagger.annotations.*;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/votingSystem/result")
public class ResultController {
    private final ResultService service;
    @Autowired
    public ResultController(ResultService service){
        this.service = service;
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

    @GetMapping(value = "/{sessionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultResponseDTO> getResult(@PathVariable long sessionId) {
        ResultResponseDTO result = service.getResultBySession(sessionId);
        return ResponseEntity.ok(result);
    }



}

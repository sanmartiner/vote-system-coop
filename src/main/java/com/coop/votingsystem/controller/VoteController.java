package com.coop.votingsystem.controller;

import com.coop.votingsystem.dto.request.VoteRequestDTO;
import com.coop.votingsystem.dto.response.VoteResponseDTO;
import com.coop.votingsystem.model.interfaces.VoteService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@RestController
@RequestMapping("/api/v1/votingSystem/vote")
public class VoteController {

    public final VoteService voteService;

       @Autowired
        public VoteController(VoteService voteService) {
            this.voteService = voteService;
        }


        @ApiOperation(value = "Vote register of a associate by topic ", response = VoteResponseDTO.class)
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

        @ResponseStatus(HttpStatus.CREATED)
        @PostMapping("/register")
        public ResponseEntity<VoteResponseDTO> save(@RequestBody VoteRequestDTO vote) throws URISyntaxException {
            VoteResponseDTO response = this.voteService.register(vote.getTopicsId(), vote.getAssociateID(), vote.getVote());
            return ResponseEntity.created(new URI(response.toString())).body(response);
        }



}

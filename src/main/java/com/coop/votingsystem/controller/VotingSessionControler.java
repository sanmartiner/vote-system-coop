package com.coop.votingsystem.controller;

import com.coop.votingsystem.dto.response.VoteResponseDTO;
import com.coop.votingsystem.exceptionhandler.VotingSessionException;
import com.coop.votingsystem.model.entitiy.VotingSession;
import com.coop.votingsystem.model.entitiy.Topics;
import com.coop.votingsystem.model.interfaces.TopicsService;
import com.coop.votingsystem.model.interfaces.VotingSessionService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
@Slf4j
@RestController
@RequestMapping("api/v1/votingSystem/session")
public class VotingSessionControler {

    private final VotingSessionService votingSessionService;
    private final TopicsService topicsService;
    @Autowired
    public VotingSessionControler(VotingSessionService votingSessionService, TopicsService topicsService) {
        this.votingSessionService = votingSessionService;
        this.topicsService = topicsService;
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
    @PostMapping("/open/{topicId}")
    public ResponseEntity<?> openSession(@PathVariable Long topicId, @RequestParam(required = false) LocalDateTime end) {
        try {
            Topics topic = topicsService.getById(topicId);

            LocalDateTime endSession = (end != null) ? end : LocalDateTime.now().plusMinutes(1);

            VotingSession votingSession = votingSessionService.openSession(topic, endSession);

            return ResponseEntity.created(getSessionUri(votingSession.getId())).build();
        } catch (VotingSessionException e) {

           return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private URI getSessionUri(Long sessionId) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{sessionId}")
                .buildAndExpand(sessionId)
                .toUri();
    }

}

package com.coop.votingsystem.controller;

import com.coop.votingsystem.model.entitiy.VotingSession;
import com.coop.votingsystem.model.entitiy.Topics;
import com.coop.votingsystem.model.interfaces.TopicsService;
import com.coop.votingsystem.model.interfaces.VotingSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
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

    @PostMapping("/open/{topicId}")
    public ResponseEntity<?> openSession(@PathVariable Long topicId, @RequestParam(required = false) LocalDateTime end) {
        try {
            Topics topic = topicsService.getById(topicId);

            LocalDateTime endSession = (end != null) ? end : LocalDateTime.now().plusMinutes(1);

            VotingSession votingSession = votingSessionService.(topicId, endSession);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(votingSession.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (SessaoVotacaoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

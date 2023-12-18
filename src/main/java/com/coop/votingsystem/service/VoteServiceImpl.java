package com.coop.votingsystem.service;

import com.coop.votingsystem.dto.response.VoteResponseDTO;
import com.coop.votingsystem.exceptionhandler.VotingSystemExceptionHandler;
import com.coop.votingsystem.integration.validateassociate.AssociateIdService;
import com.coop.votingsystem.model.entitiy.Vote;
import com.coop.votingsystem.model.entitiy.VotingSession;
import com.coop.votingsystem.model.enums.VoteType;
import com.coop.votingsystem.model.interfaces.TopicsService;
import com.coop.votingsystem.model.interfaces.VoteService;
import com.coop.votingsystem.model.interfaces.VotingSessionService;
import com.coop.votingsystem.repository.AssociateRepository;
import com.coop.votingsystem.repository.TopicRepository;
import com.coop.votingsystem.repository.VoteRepository;
import com.coop.votingsystem.repository.VotingSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class VoteServiceImpl implements VoteService {

    private final AssociateRepository associateRepository;
    private final VoteRepository voteRepository;
    private final VotingSessionService votingSessionService;
    private final TopicRepository topicRepository;
    private final VotingSessionRepository votingSessionRepository;

    @Autowired
    public VoteServiceImpl(AssociateRepository associateRepository, VoteRepository voteRepository, VotingSessionService votingSessionService, TopicRepository topicRepository, VotingSessionRepository votingSessionRepository) {
        this.associateRepository = associateRepository;
        this.voteRepository = voteRepository;
        this.votingSessionService = votingSessionService;
        this.topicRepository = topicRepository;
        this.votingSessionRepository = votingSessionRepository;
    }

    @Override
    public VoteResponseDTO register(Long topicsId, Long associateID, String vote) {

        if (AssociateIdService.checkValidCpf(associateID)) {
            new VotingSystemExceptionHandler("Invalid CPF register!");
        }

        if (associateRepository.findById(associateID).isEmpty()) {
            new VotingSystemExceptionHandler("Invalid associate register. Associate not registered");
        }

        if (!vote.equals(VoteType.NO) && !vote.equals(VoteType.YES)) {
            new VotingSystemExceptionHandler("Invalid vote");
        }

        if (!votingSessionService.sessionExists(topicRepository.getReferenceById(topicsId), LocalDateTime.now())) {
            new VotingSystemExceptionHandler("Have not a session open to this topic.");
        } else {
            Long sessionId = votingSessionRepository.findByTopicsId(topicsId).getId();
            voteRepository.save(Vote.builder().value(vote).associateId(associateID).sessionId(sessionId).build()).getId();
        }

        return VoteResponseDTO.builder().vote(vote).associateId(associateID).topicId(topicsId).build();
    }
}




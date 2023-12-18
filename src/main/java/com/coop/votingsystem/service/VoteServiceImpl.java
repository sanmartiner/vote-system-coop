package com.coop.votingsystem.service;

import com.coop.votingsystem.dto.response.VoteResponseDTO;
import com.coop.votingsystem.exceptionhandler.VotingSessionException;
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
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

@Service
public class VoteServiceImpl implements VoteService {

    private final AssociateRepository associateRepository;
    private final VoteRepository voteRepository;
    private final VotingSessionService votingSessionService;
    private final TopicRepository topicRepository;
    private final VotingSessionRepository votingSessionRepository;
    private final MessageSource messageSource;

    @Autowired
    public VoteServiceImpl(AssociateRepository associateRepository, VoteRepository voteRepository, VotingSessionService votingSessionService, TopicRepository topicRepository, VotingSessionRepository votingSessionRepository, MessageSource messageSource) {
        this.associateRepository = associateRepository;
        this.voteRepository = voteRepository;
        this.votingSessionService = votingSessionService;
        this.topicRepository = topicRepository;
        this.votingSessionRepository = votingSessionRepository;
        this.messageSource = messageSource;
    }

    @Override
    public VoteResponseDTO register(Long topicsId, Long associateID, String vote) {

        if (!AssociateIdService.checkValidCpf(associateID)) {
            throw new VotingSessionException(messageSource.getMessage("associate.id.invalid", null, LocaleContextHolder.getLocale()));
        }

        if (associateRepository.findById(associateID).isEmpty()) {
            throw new VotingSessionException(messageSource.getMessage("associate.not.registered", null, LocaleContextHolder.getLocale()));
        }

        if (!vote.equals(VoteType.NO.toString()) && !vote.equals(VoteType.YES.toString())) {
            throw new VotingSessionException(messageSource.getMessage("vote.invalid", null, LocaleContextHolder.getLocale()));
        }

        if (!votingSessionService.sessionExists(topicRepository.getReferenceById(topicsId), LocalDateTime.now())) {
            throw new VotingSessionException(messageSource.getMessage("session.not.exists", null, LocaleContextHolder.getLocale()));
        } else {
            Long sessionId = votingSessionRepository.findByTopicsId_Id(topicsId).getId();
            voteRepository.save(Vote.builder().value(vote).associateId(associateID).sessionId(sessionId).build());
        }

        return VoteResponseDTO.builder().vote(vote).associateId(associateID).topicId(topicsId).build();

    }


}




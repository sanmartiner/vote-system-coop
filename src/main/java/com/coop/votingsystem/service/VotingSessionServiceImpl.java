package com.coop.votingsystem.service;

import com.coop.votingsystem.exceptionhandler.VotingSessionException;
import com.coop.votingsystem.model.entitiy.Topics;
import com.coop.votingsystem.model.entitiy.VotingSession;
import com.coop.votingsystem.model.interfaces.VotingSessionService;
import com.coop.votingsystem.repository.VotingSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VotingSessionServiceImpl implements VotingSessionService {

    private final VotingSessionRepository repository;


    @Autowired
    public VotingSessionServiceImpl(VotingSessionRepository repository) {
        this.repository = repository;
    }


    public VotingSession openSession(Topics topic, LocalDateTime end) {

        if (repository.existsByTopicEndAfter(topic, LocalDateTime.now())) {
            throw new VotingSessionException("Have an open session to this topic");
        }

        if (end.isBefore(LocalDateTime.now())) {
            throw new VotingSessionException("Invalid end date, the date should be on the future");
        }

        VotingSession newSession = VotingSession.builder().topicsId(topic.getId().getId()).startDate(LocalDateTime.now()).endDate(end).build();


        return repository.save(newSession);
    }
}







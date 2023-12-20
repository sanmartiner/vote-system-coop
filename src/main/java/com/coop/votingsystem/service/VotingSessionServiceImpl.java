package com.coop.votingsystem.service;

import com.coop.votingsystem.exceptionhandler.VotingSessionException;
import com.coop.votingsystem.model.entitiy.Topics;
import com.coop.votingsystem.model.entitiy.VotingSession;
import com.coop.votingsystem.model.interfaces.VotingSessionService;
import com.coop.votingsystem.repository.VotingSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class VotingSessionServiceImpl implements VotingSessionService {

    private final VotingSessionRepository repository;


    @Autowired
    public VotingSessionServiceImpl(VotingSessionRepository repository) {
        this.repository = repository;
    }


    public VotingSession openSession(Topics topic, LocalDateTime end) {

        if (this.sessionExists(topic, LocalDateTime.now())) {
            throw new VotingSessionException("Have an open session to this topic");
        }

        if (end.isBefore(LocalDateTime.now())) {
            throw new VotingSessionException("Invalid end date, the date should be on the future");
        }

        return repository.save(VotingSession.builder().endDate(end).startDate(LocalDateTime.now())
                .topicsId(topic).build());
    }

    @Override
    public boolean sessionExists(Topics topic, LocalDateTime now) {
        return !Objects.isNull(repository.findByTopicsId_Id(topic.getTopicsEntityId().getId()));
    }

    @Override
    public VotingSession save(VotingSession newSession) {
        return null;
    }
}







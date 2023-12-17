package com.coop.votingsystem.repository;

import com.coop.votingsystem.model.entitiy.Topics;
import com.coop.votingsystem.model.entitiy.VotingSession;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface VotingSessionRepository {
    boolean existsByTopicEndAfter(Topics topic, LocalDateTime now);

    VotingSession save(VotingSession newSession);
}

package com.coop.votingsystem.model.interfaces;

import com.coop.votingsystem.model.entitiy.Topics;
import com.coop.votingsystem.model.entitiy.VotingSession;

import java.time.LocalDateTime;

public interface VotingSessionService {

    VotingSession openSession(Topics topic, LocalDateTime end);

    boolean sessionExists(Topics topic, LocalDateTime now);

    VotingSession save(VotingSession newSession);
}

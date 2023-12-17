package com.coop.votingsystem.model.interfaces;

import com.coop.votingsystem.model.entitiy.Topics;
import com.coop.votingsystem.model.entitiy.VotingSession;

import java.time.LocalDateTime;

public interface VotingSessionService {

    public VotingSession openSession(Topics topic, LocalDateTime end);

}

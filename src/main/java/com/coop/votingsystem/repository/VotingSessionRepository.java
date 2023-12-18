package com.coop.votingsystem.repository;

import com.coop.votingsystem.model.entitiy.Topics;
import com.coop.votingsystem.model.entitiy.VotingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface VotingSessionRepository extends JpaRepository<VotingSession, Long> {

    VotingSession findByTopicsId(Long id);

}

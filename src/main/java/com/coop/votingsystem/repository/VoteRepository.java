package com.coop.votingsystem.repository;

import com.coop.votingsystem.model.entitiy.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    List<Vote> findAllBySessionId(Long sessionId);
}

package com.coop.votingsystem.repository;

import com.coop.votingsystem.model.entitiy.VotingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VotingSessionRepository extends JpaRepository<VotingSession, Long> {

    @Query("SELECT vs FROM VotingSession vs WHERE vs.topicsId.topicsEntityId.id = :topicsId")
    VotingSession findByTopicsId_Id(@Param("topicsId") final Long topicsId);
}

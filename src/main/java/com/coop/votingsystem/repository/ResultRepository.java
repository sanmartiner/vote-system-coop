package com.coop.votingsystem.repository;

import com.coop.votingsystem.model.entitiy.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<Vote, Long> {
}

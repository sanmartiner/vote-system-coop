package com.coop.votingsystem.repository;

import com.coop.votingsystem.model.Entitiy.Topics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicsRepository extends JpaRepository<Topics, Long> {
}

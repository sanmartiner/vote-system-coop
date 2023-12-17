package com.coop.votingsystem.repository;

import com.coop.votingsystem.model.entitiy.Topics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topics, Long> {
}

package com.coop.votingsystem.repository;

import com.coop.votingsystem.model.entitiy.Associate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociateRepository extends JpaRepository<Associate, Long> {
}

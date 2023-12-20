package com.coop.votingsystem.repository;

import com.coop.votingsystem.model.entitiy.Topics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topics, Long> {

//    @Query("SELECT t FROM Topics t WHERE t.topicsEntityId.id = :topicsId")
//    Topics getById(@Param("topicsId") final Long topicsId);

    Topics findByTopicsEntityIdId(Long id);

    Topics findByTopicsEntityIdTitle(String title);
}

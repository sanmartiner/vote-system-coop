package com.coop.votingsystem.model.interfaces;

import com.coop.votingsystem.dto.response.TopicResponseDTO;
import com.coop.votingsystem.model.entitiy.Topics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TopicsService {
    Long topicsRegister(Topics topics);

    Page<Topics> getAll(Pageable pageable);

    Topics getById(Long id);
}



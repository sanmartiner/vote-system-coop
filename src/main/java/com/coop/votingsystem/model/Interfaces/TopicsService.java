package com.coop.votingsystem.model.Interfaces;

import com.coop.votingsystem.dto.TopicsResponseDTO;
import com.coop.votingsystem.model.Entitiy.Topics;

import java.util.List;

public interface TopicsService {
    TopicsResponseDTO topicsRegister(Topics topics);

    List<TopicsResponseDTO> getAll();

    TopicsResponseDTO getById(Long id);
}



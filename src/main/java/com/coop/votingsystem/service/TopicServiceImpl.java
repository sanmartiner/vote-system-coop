package com.coop.votingsystem.service;

import com.coop.votingsystem.dto.response.TopicResponseDTO;
import com.coop.votingsystem.model.entitiy.Topics;
import com.coop.votingsystem.model.interfaces.TopicsService;
import com.coop.votingsystem.repository.TopicRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicServiceImpl implements TopicsService {

    private final TopicRepository repository;

    @Autowired
    public TopicServiceImpl(TopicRepository repository, ModelMapper modelMapper){
    this.repository = repository;
    }
    @Override
    public Long topicsRegister(Topics topics) {
        return repository.save(topics).getId().getId();

    }

    @Override
    public Page<Topics> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Topics getById(Long id) {
        Optional<Topics> topicsOptional = repository.findById(id);
        if (topicsOptional.isPresent()) {
            return topicsOptional.get();
        } else {
            throw new EntityNotFoundException("Topic with ID " + id + " was not found");
        }
    }



}

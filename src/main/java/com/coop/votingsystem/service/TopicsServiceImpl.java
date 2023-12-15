package com.coop.votingsystem.service;

import com.coop.votingsystem.dto.TopicsResponseDTO;
import com.coop.votingsystem.model.Entitiy.Topics;
import com.coop.votingsystem.model.Interfaces.TopicsService;
import com.coop.votingsystem.repository.TopicsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicsServiceImpl implements TopicsService {

    private final TopicsRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public TopicsServiceImpl(TopicsRepository repository, ModelMapper modelMapper){
    this.modelMapper = modelMapper;
    this.repository = repository;
    }
    @Override
    public TopicsResponseDTO topicsRegister(Topics topics) {
        Topics savedTopics = repository.save(topics);
        return modelMapper.map(savedTopics, TopicsResponseDTO.class);
    }

    @Override
    public List<TopicsResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(topic -> modelMapper.map(topic, TopicsResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TopicsResponseDTO getById(Long id) {
        Optional<Topics> topicsOptional = repository.findById(id);
        if (topicsOptional.isPresent()) {
            Topics topics = topicsOptional.get();
            return modelMapper.map(topics, TopicsResponseDTO.class);
        } else {
            throw new EntityNotFoundException("Topic with ID " + id + " was not found");
        }
    }


}

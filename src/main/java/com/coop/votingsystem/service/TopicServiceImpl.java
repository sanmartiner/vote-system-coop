package com.coop.votingsystem.service;

import com.coop.votingsystem.exceptionhandler.TopicCreateException;
import com.coop.votingsystem.model.entitiy.Topics;
import com.coop.votingsystem.model.interfaces.TopicsService;
import com.coop.votingsystem.repository.TopicRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Objects;
import java.util.Optional;

@Service
public class TopicServiceImpl implements TopicsService {

    private final TopicRepository repository;


    @Autowired
    public TopicServiceImpl(TopicRepository repository){
    this.repository = repository;
    }
    @Override
    public Long topicsRegister(Topics topics) {
        Topics t = repository.findByTopicsEntityIdTitle(topics.getTopicsEntityId().getTitle());

        if(!Objects.isNull(t)) {
            if (Objects.equals(t.getTopicsEntityId().getTitle(), topics.getTopicsEntityId().getTitle()) &&
                    t.getVotingDate().equals(topics.getVotingDate())) {
                throw new TopicCreateException("Has a topic registered with the same title and same voting date");
            }
        }

        return repository.save(topics).getTopicsEntityId().getId();
    }

    @Override
    public Page<Topics> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Topics getById(Long id) {
        Optional<Topics> topicsOptional = Optional.ofNullable(repository.findByTopicsEntityIdId(id));
        if (topicsOptional.isPresent()) {
            return topicsOptional.get();
        } else {
            throw new EntityNotFoundException("Topic with ID " + id + " was not found");
        }
    }



}

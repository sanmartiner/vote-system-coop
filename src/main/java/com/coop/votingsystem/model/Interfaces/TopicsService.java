package com.coop.votingsystem.model.Interfaces;

import com.coop.votingsystem.model.Entitiy.Topics;

import java.util.List;

public interface TopicsService {
    Topics topicsRegister(Topics topics);
    List<Topics> getAll(Topics topics);

    Topics getById(Long id);
}

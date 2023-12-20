package com.coop.votingsystem.service;

import com.coop.votingsystem.model.entitiy.Topics;
import com.coop.votingsystem.model.entitiy.TopicsEntityId;
import com.coop.votingsystem.repository.TopicRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TopicServiceImplTest {

    @Mock
    private TopicRepository mockRepository;

    private TopicServiceImpl topicServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        topicServiceImplUnderTest = new TopicServiceImpl(mockRepository);
    }

    @Test
    void testTopicsRegister() {
        // Setup
        final Topics topics = Topics.builder()
                .topicsEntityId(TopicsEntityId.builder()
                        .id(0L)
                        .title("title")
                        .build())
                .votingDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .build();

        // Configure TopicRepository.findByTopicsEntityIdTitle(...).
        final Topics topics1 = Topics.builder()
                .topicsEntityId(TopicsEntityId.builder()
                        .id(0L)
                        .title("title")
                        .build())
                .votingDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .build();
        when(mockRepository.findByTopicsEntityIdTitle("title")).thenReturn(topics1);

        // Configure TopicRepository.save(...).
        final Topics topics2 = Topics.builder()
                .topicsEntityId(TopicsEntityId.builder()
                        .id(0L)
                        .title("title")
                        .build())
                .votingDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .build();
        when(mockRepository.save(Topics.builder()
                .topicsEntityId(TopicsEntityId.builder()
                        .id(0L)
                        .title("title")
                        .build())
                .votingDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .build())).thenReturn(topics2);

        // Run the test
        final Long result = topicServiceImplUnderTest.topicsRegister(topics);

        // Verify the results
        assertThat(result).isEqualTo(0L);
    }

    @Test
    void testGetAll() {
        // Setup
        // Configure TopicRepository.findAll(...).
        final Page<Topics> topics = new PageImpl<>(List.of(Topics.builder()
                .topicsEntityId(TopicsEntityId.builder()
                        .id(0L)
                        .title("title")
                        .build())
                .votingDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .build()));
        when(mockRepository.findAll(any(Pageable.class))).thenReturn(topics);

        // Run the test
        final Page<Topics> result = topicServiceImplUnderTest.getAll(PageRequest.of(0, 1));

        // Verify the results
    }

    @Test
    void testGetAll_TopicRepositoryReturnsNoItems() {
        // Setup
        when(mockRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));

        // Run the test
        final Page<Topics> result = topicServiceImplUnderTest.getAll(PageRequest.of(0, 1));

        // Verify the results
    }

    @Test
    void testGetById() {
        // Setup
        final Topics expectedResult = Topics.builder()
                .topicsEntityId(TopicsEntityId.builder()
                        .id(0L)
                        .title("title")
                        .build())
                .votingDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .build();

        // Configure TopicRepository.findByTopicsEntityIdId(...).
        final Topics topics = Topics.builder()
                .topicsEntityId(TopicsEntityId.builder()
                        .id(0L)
                        .title("title")
                        .build())
                .votingDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .build();
        when(mockRepository.findByTopicsEntityIdId(0L)).thenReturn(topics);

        // Run the test
        final Topics result = topicServiceImplUnderTest.getById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetById_TopicRepositoryReturnsNull() {
        // Setup
        when(mockRepository.findByTopicsEntityIdId(0L)).thenReturn(null);

        // Run the test
        assertThatThrownBy(() -> topicServiceImplUnderTest.getById(0L)).isInstanceOf(EntityNotFoundException.class);
    }
}

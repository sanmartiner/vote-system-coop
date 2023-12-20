package com.coop.votingsystem.service;

import com.coop.votingsystem.model.entitiy.Topics;
import com.coop.votingsystem.model.entitiy.TopicsEntityId;
import com.coop.votingsystem.model.entitiy.VotingSession;
import com.coop.votingsystem.repository.VotingSessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VotingSessionServiceImplTest {

    @Mock
    private VotingSessionRepository mockRepository;

    private VotingSessionServiceImpl votingSessionServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        votingSessionServiceImplUnderTest = new VotingSessionServiceImpl(mockRepository);
    }

    @Test
    void testOpenSession() {
        // Setup
        final Topics topic = Topics.builder()
                .topicsEntityId(TopicsEntityId.builder()
                        .id(0L)
                        .build())
                .build();
        final VotingSession expectedResult = VotingSession.builder()
                .topicsId(Topics.builder()
                        .topicsEntityId(TopicsEntityId.builder()
                                .id(0L)
                                .build())
                        .build())
                .startDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .build();

        // Configure VotingSessionRepository.save(...).
        final VotingSession votingSession = VotingSession.builder()
                .topicsId(Topics.builder()
                        .topicsEntityId(TopicsEntityId.builder()
                                .id(0L)
                                .build())
                        .build())
                .startDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .build();
        when(mockRepository.save(VotingSession.builder()
                .topicsId(Topics.builder()
                        .topicsEntityId(TopicsEntityId.builder()
                                .id(0L)
                                .build())
                        .build())
                .startDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .build())).thenReturn(votingSession);

        // Run the test
        final VotingSession result = votingSessionServiceImplUnderTest.openSession(topic,
                LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSessionExists() {
        // Setup
        final Topics topic = Topics.builder()
                .topicsEntityId(TopicsEntityId.builder()
                        .id(0L)
                        .build())
                .build();

        // Configure VotingSessionRepository.findByTopicsId_Id(...).
        final VotingSession votingSession = VotingSession.builder()
                .topicsId(Topics.builder()
                        .topicsEntityId(TopicsEntityId.builder()
                                .id(0L)
                                .build())
                        .build())
                .startDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .build();
        when(mockRepository.findByTopicsId_Id(0L)).thenReturn(votingSession);

        // Run the test
        final boolean result = votingSessionServiceImplUnderTest.sessionExists(topic,
                LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        // Verify the results
        assertThat(result).isTrue();
    }

    @Test
    void testSave() {
        assertThat(votingSessionServiceImplUnderTest.save(VotingSession.builder()
                .topicsId(Topics.builder()
                        .topicsEntityId(TopicsEntityId.builder()
                                .id(0L)
                                .build())
                        .build())
                .startDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .build())).isNull();
    }
}

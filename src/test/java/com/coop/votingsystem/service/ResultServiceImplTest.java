package com.coop.votingsystem.service;

import com.coop.votingsystem.dto.response.ResultResponseDTO;
import com.coop.votingsystem.model.entitiy.Vote;
import com.coop.votingsystem.repository.VoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResultServiceImplTest {

    @Mock
    private VoteRepository mockVoteRepository;

    private ResultServiceImpl resultServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        resultServiceImplUnderTest = new ResultServiceImpl(mockVoteRepository);
    }

    @Test
    void testGetResultBySession() {
        // Setup
        final ResultResponseDTO expectedResult = ResultResponseDTO.builder()
                .topicId(0L)
                .totalVote(0L)
                .votesYes(0L)
                .votesNo(0L)
                .build();

        // Configure VoteRepository.findAllBySessionId(...).
        final List<Vote> votes = List.of(Vote.builder()
                .value("value")
                .build());
        when(mockVoteRepository.findAllBySessionId(0L)).thenReturn(votes);

        // Run the test
        final ResultResponseDTO result = resultServiceImplUnderTest.getResultBySession(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetResultBySession_VoteRepositoryReturnsNoItems() {
        // Setup
        final ResultResponseDTO expectedResult = ResultResponseDTO.builder()
                .topicId(0L)
                .totalVote(0L)
                .votesYes(0L)
                .votesNo(0L)
                .build();
        when(mockVoteRepository.findAllBySessionId(0L)).thenReturn(Collections.emptyList());

        // Run the test
        final ResultResponseDTO result = resultServiceImplUnderTest.getResultBySession(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}

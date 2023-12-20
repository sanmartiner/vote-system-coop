package com.coop.votingsystem.service;

import com.coop.votingsystem.dto.response.VoteResponseDTO;
import com.coop.votingsystem.exceptionhandler.VotingSessionException;
import com.coop.votingsystem.model.entitiy.Associate;
import com.coop.votingsystem.model.entitiy.Topics;
import com.coop.votingsystem.model.entitiy.Vote;
import com.coop.votingsystem.model.entitiy.VotingSession;
import com.coop.votingsystem.model.interfaces.VotingSessionService;
import com.coop.votingsystem.repository.AssociateRepository;
import com.coop.votingsystem.repository.TopicRepository;
import com.coop.votingsystem.repository.VoteRepository;
import com.coop.votingsystem.repository.VotingSessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VoteServiceImplTest {

    @Mock
    private AssociateRepository mockAssociateRepository;
    @Mock
    private VoteRepository mockVoteRepository;
    @Mock
    private VotingSessionService mockVotingSessionService;
    @Mock
    private TopicRepository mockTopicRepository;
    @Mock
    private VotingSessionRepository mockVotingSessionRepository;
    @Mock
    private MessageSource mockMessageSource;

    private VoteServiceImpl voteServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        voteServiceImplUnderTest = new VoteServiceImpl(mockAssociateRepository, mockVoteRepository,
                mockVotingSessionService, mockTopicRepository, mockVotingSessionRepository, mockMessageSource);
    }

    @Test
    void testRegister() {
        // Setup
        final VoteResponseDTO expectedResult = VoteResponseDTO.builder()
                .associateId(0L)
                .topicId(0L)
                .vote("vote")
                .build();
        when(mockAssociateRepository.findById(0L)).thenReturn(Optional.of(Associate.builder().build()));
        when(mockTopicRepository.getReferenceById(0L)).thenReturn(Topics.builder().build());
        when(mockVotingSessionService.sessionExists(Topics.builder().build(),
                LocalDateTime.of(2020, 1, 1, 0, 0, 0))).thenReturn(true);
        when(mockVotingSessionRepository.findByTopicsId_Id(0L)).thenReturn(VotingSession.builder()
                .id(0L)
                .build());

        // Run the test
        final VoteResponseDTO result = voteServiceImplUnderTest.register(0L, 0L, "vote");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockVoteRepository).save(Vote.builder()
                .associateId(0L)
                .value("vote")
                .sessionId(0L)
                .build());
    }

    @Test
    void testRegister_MessageSourceThrowsNoSuchMessageException() {
        // Setup
        when(mockMessageSource.getMessage(eq("associate.id.invalid"), any(Object[].class), eq(Locale.US)))
                .thenThrow(NoSuchMessageException.class);

        // Run the test
        assertThatThrownBy(() -> voteServiceImplUnderTest.register(0L, 0L, "vote"))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void testRegister_AssociateRepositoryReturnsAbsent() {
        // Setup
        when(mockAssociateRepository.findById(0L)).thenReturn(Optional.empty());
        when(mockMessageSource.getMessage(eq("associate.id.invalid"), any(Object[].class), eq(Locale.US)))
                .thenReturn("result");

        // Run the test
        assertThatThrownBy(() -> voteServiceImplUnderTest.register(0L, 0L, "vote"))
                .isInstanceOf(VotingSessionException.class);
    }

    @Test
    void testRegister_VotingSessionServiceReturnsFalse() {
        // Setup
        when(mockAssociateRepository.findById(0L)).thenReturn(Optional.of(Associate.builder().build()));
        when(mockTopicRepository.getReferenceById(0L)).thenReturn(Topics.builder().build());
        when(mockVotingSessionService.sessionExists(Topics.builder().build(),
                LocalDateTime.of(2020, 1, 1, 0, 0, 0))).thenReturn(false);
        when(mockMessageSource.getMessage(eq("associate.id.invalid"), any(Object[].class), eq(Locale.US)))
                .thenReturn("result");

        // Run the test
        assertThatThrownBy(() -> voteServiceImplUnderTest.register(0L, 0L, "vote"))
                .isInstanceOf(VotingSessionException.class);
    }
}

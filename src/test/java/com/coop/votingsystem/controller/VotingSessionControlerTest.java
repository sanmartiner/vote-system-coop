package com.coop.votingsystem.controller;

import com.coop.votingsystem.model.entitiy.Topics;
import com.coop.votingsystem.model.entitiy.VotingSession;
import com.coop.votingsystem.model.interfaces.TopicsService;
import com.coop.votingsystem.model.interfaces.VotingSessionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(VotingSessionControler.class)
class VotingSessionControlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VotingSessionService mockVotingSessionService;
    @MockBean
    private TopicsService mockTopicsService;

    @Test
    void testOpenSession() throws Exception {
        // Setup
        when(mockTopicsService.getById(0L)).thenReturn(Topics.builder().build());
        when(mockVotingSessionService.openSession(Topics.builder().build(),
                LocalDateTime.of(2020, 1, 1, 0, 0, 0))).thenReturn(VotingSession.builder()
                .id(0L)
                .build());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("api/v1/votingSystem/session/open/{topicId}", 0)
                        .param("end", "2020-01-01")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}

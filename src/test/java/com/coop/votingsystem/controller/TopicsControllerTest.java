package com.coop.votingsystem.controller;

import com.coop.votingsystem.model.entitiy.Topics;
import com.coop.votingsystem.model.entitiy.TopicsEntityId;
import com.coop.votingsystem.model.interfaces.TopicsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TopicsController.class)
class TopicsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TopicsService mockService;
    @MockBean
    private ModelMapper mockModelMapper;

    @Test
    void testTopicsRegister() throws Exception {
        // Setup
        when(mockService.topicsRegister(Topics.builder()
                .topicsEntityId(TopicsEntityId.builder()
                        .id(0L)
                        .title("title")
                        .build())
                .description("description")
                .votingDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .build())).thenReturn(0L);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("api/v1/votingSystem/topics/save")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetOne() throws Exception {
        // Setup
        // Configure TopicsService.getById(...).
        final Topics topics = Topics.builder()
                .topicsEntityId(TopicsEntityId.builder()
                        .id(0L)
                        .title("title")
                        .build())
                .description("description")
                .votingDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .build();
        when(mockService.getById(0L)).thenReturn(topics);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("api/v1/votingSystem/topics/getOne/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetAll() throws Exception {
        // Setup
        // Configure TopicsService.getAll(...).
        final Page<Topics> topics = new PageImpl<>(List.of(Topics.builder()
                .topicsEntityId(TopicsEntityId.builder()
                        .id(0L)
                        .title("title")
                        .build())
                .description("description")
                .votingDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0))
                .build()));
        when(mockService.getAll(any(Pageable.class))).thenReturn(topics);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("api/v1/votingSystem/topics/getAll")
                        .param("size", "0")
                        .param("page", "0")
                        .param("direction", "direction")
                        .param("sortField", "sortField")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetAll_TopicsServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockService.getAll(any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("api/v1/votingSystem/topics/getAll")
                        .param("size", "0")
                        .param("page", "0")
                        .param("direction", "direction")
                        .param("sortField", "sortField")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}

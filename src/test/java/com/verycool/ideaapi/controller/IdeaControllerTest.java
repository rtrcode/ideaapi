package com.verycool.ideaapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.verycool.ideaapi.model.Idea;
import com.verycool.ideaapi.model.User;
import com.verycool.ideaapi.repository.IdeaRepository;
import com.verycool.ideaapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class IdeaControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private IdeaRepository ideaRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    void shouldGetSingleIdea() throws Exception {
        //given
        User newUser = new User();
        newUser.setUsername("testUser");
        newUser.setPassword("test123");
        newUser.setCreated(LocalDateTime.now());

        userRepository.save(newUser);

        Idea newIdea = new Idea();

        newIdea.setUser(newUser);
        newIdea.setTitle("Test");
        newIdea.setContent("Test Content");
        newIdea.setCreated(LocalDateTime.now());
        ideaRepository.save(newIdea);

        //when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/ideas/ " + newIdea.getId() ))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        //then
        Idea idea = objectMapper.readValue(result.getResponse().getContentAsString(), Idea.class);
        assertThat(idea).isNotNull();
        assertThat(idea.getId()).isEqualTo(newIdea.getId());
        assertThat(idea.getTitle()).isEqualTo("Test");
        assertThat(idea.getContent()).isEqualTo("Test Content");
    }

    @Test
    @Transactional
    void shouldGetIdeas() throws Exception {
        //given
        User newUser = new User();
        newUser.setUsername("testUser");
        newUser.setPassword("test123");
        newUser.setCreated(LocalDateTime.now());

        userRepository.save(newUser);

        Idea newIdea1 = new Idea();
        newIdea1.setTitle("Test1");
        newIdea1.setContent("Test Content1");
        newIdea1.setCreated(LocalDateTime.now());
        newIdea1.setUser(newUser);
        ideaRepository.save(newIdea1);

        Idea newIdea2 = new Idea();
        newIdea2.setTitle("Test2");
        newIdea2.setContent("Test Content2");
        newIdea2.setCreated(LocalDateTime.now());
        newIdea2.setUser(newUser);
        ideaRepository.save(newIdea2);

        //when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/ideas?page=1&direction=ASC"))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        //then
        Idea[] ideas = objectMapper.readValue(result.getResponse().getContentAsString(), Idea[].class);
        assertThat(ideas.length > 2);
    }
}
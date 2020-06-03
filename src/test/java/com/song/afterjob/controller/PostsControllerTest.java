package com.song.afterjob.controller;

import com.song.afterjob.domain.PostsDvo;
import com.song.afterjob.repository.PostsRepository;
import com.song.afterjob.service.PostsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(controllers = PostsController.class)
@AutoConfigureMockMvc
class PostsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostsServiceImpl service;

    @MockBean
    private PostsRepository repository;

    @Autowired
    PostsRepository postsRepository;

    @Test
    void getAllPosts() throws Exception{
        postsRepository.save(new PostsDvo("title1", "abcd", "이종철"));
        postsRepository.save(new PostsDvo("title2", "abcd", "이종철"));
        postsRepository.save(new PostsDvo("title3", "abcd", "이종철"));
        postsRepository.save(new PostsDvo("title4", "abcd", "이종철"));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/posts/list")
                            .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void createPost() {
    }
}
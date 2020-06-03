package com.song.afterjob.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostsDvoTest {

    @Test
    void getTitle() {
        final PostsDvo postsDvo = PostsDvo.builder()
                .title("title")
                .cont("abc")
                .build();
        final String title = postsDvo.getTitle();
        assertEquals(postsDvo.getTitle(), title);
    }

   // @Test
    void getCont() {
    }
}
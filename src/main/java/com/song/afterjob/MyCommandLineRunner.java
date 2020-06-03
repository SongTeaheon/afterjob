package com.song.afterjob;

import com.song.afterjob.domain.PostsDvo;
import com.song.afterjob.repository.PostsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MyCommandLineRunner.class);

    @Autowired
    PostsRepository postsRepository;

    @Override
    public void run(String... args) throws Exception {
        logger.info("command line runner run");
        postsRepository.save(new PostsDvo("title1", "abcd", "이종철"));
        postsRepository.save(new PostsDvo("title2", "abcd", "이종철"));
        postsRepository.save(new PostsDvo("title3", "abcd", "이종철"));
        postsRepository.save(new PostsDvo("title4", "abcd", "이종철"));
    }
}

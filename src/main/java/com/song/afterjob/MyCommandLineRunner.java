package com.song.afterjob;

import com.song.afterjob.Utils.Constants;
import com.song.afterjob.domain.PostsDvo;
import com.song.afterjob.domain.UserEntity;
import com.song.afterjob.repository.PostsRepository;
import com.song.afterjob.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MyCommandLineRunner.class);

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args){
        logger.info("command line runner run");
        postsRepository.save(new PostsDvo("title1", "abcd", "이종철", Constants.CATEGORY_SHARE_ID));
        postsRepository.save(new PostsDvo("title2", "abcd", "이종철", Constants.CATEGORY_SHARE_ID));
        postsRepository.save(new PostsDvo("title3", "abcd", "이종철", Constants.CATEGORY_GROUP_ID));
        postsRepository.save(new PostsDvo("title4", "abcd", "이종철", Constants.CATEGORY_QNA_ID));
        userRepository.save(new UserEntity("123", passwordEncoder.encode("123"), "ROLE_USER"));
    }
}

package com.song.afterjob.service;

import com.song.afterjob.domain.PostsDvo;
import com.song.afterjob.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostsServiceImpl implements PostsService {

    private PostsRepository postsRepository;

    @Autowired
    public PostsServiceImpl(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @Override
    public List<PostsDvo> findAll() {
        List<PostsDvo> posts = new ArrayList<>();
        postsRepository.findAll().forEach(e -> posts.add(e));
        return posts;
    }

    @Override
    public Optional<PostsDvo> findById(Long postNo){
        return postsRepository.findById(postNo);
    }

    @Override
    public PostsDvo save(PostsDvo post) {
        postsRepository.save(post);
        return post;
    }

    @Override
    public void deleteById(Long postNo){
        postsRepository.deleteById(postNo);
    }


    @Override
    public void updateById(Long postNo, PostsDvo post){
        //미완성
    }



}

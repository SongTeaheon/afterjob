package com.song.afterjob.service;

import com.song.afterjob.domain.PostsDvo;

import java.util.List;
import java.util.Optional;

public interface PostsService {
    public List<PostsDvo> findAll();
    public Optional<PostsDvo> findById(Long postNo);
    public PostsDvo save(PostsDvo post);
    public void deleteById(Long postNo);
    public void updateById(Long postNo, PostsDvo post);
}

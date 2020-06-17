package com.song.afterjob.service;

import com.song.afterjob.domain.PostsDvo;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PostsService {


    List<PostsDvo> findAll();
    List<PostsDvo> findAll(Long CategoryNo);
    Optional<PostsDvo> findById(Long postNo);
    PostsDvo save(PostsDvo post);
    void deleteById(Long postNo);
    void updateById(Long postNo, PostsDvo post);

    List<PostsDvo> findAllWithPaging(int pageNum, int pageSize);
}

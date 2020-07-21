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
    long count();
    long countByCategory(long categoryNo);

    List<PostsDvo> findAllWithPaging(int pageNum, int pageSize);
    List<PostsDvo> findByCategoryWithPaging(long categoryNo, int pageNum, int pageSize)
;}

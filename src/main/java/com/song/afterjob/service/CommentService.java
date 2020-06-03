package com.song.afterjob.service;

import com.song.afterjob.domain.CommentDvo;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    public List<CommentDvo> findAll();
    public Optional<CommentDvo> findById(Long commentNo);
    public CommentDvo save(CommentDvo comment);
    public void deleteById(Long commentNo);
    public void updateById(Long commentNo, CommentDvo comment);
}

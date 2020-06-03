package com.song.afterjob.service;

import com.song.afterjob.domain.CommentDvo;
import com.song.afterjob.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentRepository commentRepository;

    @Override
    public List<CommentDvo> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<CommentDvo> findById(Long commentNo) {
        return commentRepository.findById(commentNo);
    }

    @Override
    public CommentDvo save(CommentDvo comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void deleteById(Long commentNo) {
        commentRepository.deleteById(commentNo);
    }

    @Override
    public void updateById(Long postNo, CommentDvo post) {
        //미완성.
    }
}

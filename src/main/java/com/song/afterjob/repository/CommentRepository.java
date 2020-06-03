package com.song.afterjob.repository;

import com.song.afterjob.domain.CommentDvo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentDvo, Long> {
}

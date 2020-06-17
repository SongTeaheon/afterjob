package com.song.afterjob.repository;

import com.song.afterjob.domain.PostsDvo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<PostsDvo, Long>, JpaSpecificationExecutor<PostsDvo> {
    //아무것도 안만들어도 crud생성

    Page<PostsDvo> findAll(Pageable pageable);
}

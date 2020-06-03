package com.song.afterjob.repository;

import com.song.afterjob.domain.PostsDvo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends JpaRepository<PostsDvo, Long> {
    //아무것도 안만들어도 crud생성
}

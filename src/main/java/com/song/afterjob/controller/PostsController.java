package com.song.afterjob.controller;

import com.song.afterjob.Utils.Constants;
import com.song.afterjob.domain.PostsDvo;
import com.song.afterjob.service.PostsService;
import com.song.afterjob.service.PostsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("posts")
@Slf4j
public class PostsController {
    @Autowired
    PostsService postsService;

    //카테고리별로 보기
    @GetMapping(value = "list/{categoryNo}",produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<PostsDvo>> getPostsByCategory(@PathVariable long categoryNo) {
        List<PostsDvo> postsList;

        if(categoryNo == Constants.CATEGORY_ALL_ID){
            postsList = postsService.findAll();
        }else {
            postsList = postsService.findAll(categoryNo);
        }
        return new ResponseEntity<>(postsList, HttpStatus.OK);
    }

    @GetMapping(value = "/pagingList",produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<PostsDvo>> getAllPostsWithPaging(@RequestParam int pageNum, @RequestParam int pageSize) {
        List<PostsDvo> postsList = postsService.findAllWithPaging(pageNum-1, pageSize);
        return new ResponseEntity<>(postsList, HttpStatus.OK);
    }

    //맞는지 확인피리요할 듯??
    @GetMapping("/{postNo}")
    public ResponseEntity<PostsDvo> getDetailPost(@PathVariable long postNo) {

        Optional<PostsDvo> postsDvoOp = postsService.findById(postNo);
        return postsDvoOp.map((postsDvo) -> new ResponseEntity<>(postsDvo, HttpStatus.OK ))
                .orElse(new ResponseEntity<>(new PostsDvo(), HttpStatus.NOT_FOUND ));
    }

    @PostMapping(value = "/new", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<PostsDvo> createPost(@RequestBody PostsDvo newPosts) {
        log.info("createPost");
        log.info(newPosts.toString());
        return new ResponseEntity<>(postsService.save(newPosts), HttpStatus.OK);
    }

    @DeleteMapping("/{postNo}")
    public void deletePost(@PathVariable long postNo) {
        postsService.deleteById(postNo);
    }

    @PutMapping("/{postNo}")
    public ResponseEntity<Object> updatePost(@RequestBody PostsDvo post, @PathVariable long postNo) {
        Optional<PostsDvo> postsOptional = postsService.findById(postNo);
        if (postsOptional.isEmpty())
            return ResponseEntity.notFound().build();
        post.setPostNo(postNo);
        postsService.save(post);
        return ResponseEntity.noContent().build();
    }
}

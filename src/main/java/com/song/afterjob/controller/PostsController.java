package com.song.afterjob.controller;

import com.song.afterjob.Utils.Constants;
import com.song.afterjob.domain.PostsDvo;
import com.song.afterjob.service.PostsService;
import com.song.afterjob.service.PostsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("posts")
public class PostsController {
    @Autowired
    PostsService postsService;

    @GetMapping(value = "/list",produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<PostsDvo>> getAllPosts() {
        List<PostsDvo> postsList = postsService.findAll();
        return new ResponseEntity<>(postsList, HttpStatus.OK);
    }

    @GetMapping(value = "/list/share",produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<PostsDvo>> getSharePosts() {
        List<PostsDvo> postsList = postsService.findAll(Constants.CATEGORY_SHARE_ID);
        return new ResponseEntity<>(postsList, HttpStatus.OK);
    }

    @GetMapping(value = "/list/group",produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<PostsDvo>> getGroupPosts() {
        List<PostsDvo> postsList = postsService.findAll(Constants.CATEGORY_GROUP_ID);
        return new ResponseEntity<>(postsList, HttpStatus.OK);
    }

    @GetMapping(value = "/list/qna",produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<PostsDvo>> getQnaPosts() {
        List<PostsDvo> postsList = postsService.findAll(Constants.CATEGORY_QNA_ID);
        return new ResponseEntity<>(postsList, HttpStatus.OK);
    }

    //맞는지 확인피리요할 듯??
    @GetMapping("/{postNo}")
    public ResponseEntity<PostsDvo> getDetailPost(@PathVariable long postNo) {

        Optional<PostsDvo> postsDvoOp = postsService.findById(postNo);
        if(postsDvoOp.isPresent()) {
            return new ResponseEntity<>(postsDvoOp.get(), HttpStatus.OK );
        }
        return new ResponseEntity<>(new PostsDvo(), HttpStatus.OK);
    }


    @PostMapping(value = "/new", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<PostsDvo> createPost(@RequestBody PostsDvo newPosts) {
        return new ResponseEntity<>(postsService.save(newPosts), HttpStatus.OK);
    }

    @DeleteMapping("/{postNo}")
    public void deletePost(@PathVariable long postNo) {
        postsService.deleteById(postNo);
    }

    @PutMapping("/{postNo}")
    public ResponseEntity<Object> updatePost(@RequestBody PostsDvo post, @PathVariable long postNo) {
        Optional<PostsDvo> postsOptional = postsService.findById(postNo);
        if (!postsOptional.isPresent())
            return ResponseEntity.notFound().build();
        post.setPostNo(postNo);
        postsService.save(post);
        return ResponseEntity.noContent().build();
    }
}

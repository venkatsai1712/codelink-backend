package com.venkatsai.codelink.controller;

import com.venkatsai.codelink.model.Comment;
import com.venkatsai.codelink.model.Like;
import com.venkatsai.codelink.model.Post;
import com.venkatsai.codelink.service.PostService;
import com.venkatsai.codelink.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin("*")
@Slf4j
public class PostController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @GetMapping("/me/posts")
    public ResponseEntity<?> getPosts(@RequestHeader("user_id") Long user_id){
        log.info("User Id"+user_id);
        return ResponseEntity.ok().body(postService.getPosts(user_id));
    }

    @PostMapping("/me/posts")
    public ResponseEntity<?> createPost(@RequestHeader("user_id") Long user_id, @RequestBody Post post){
        return ResponseEntity.ok().body(postService.createPostByUserId(user_id,post));
    }

    @PostMapping("/me/posts/{post_id}/comments")
    public ResponseEntity<?> commentPost(@RequestHeader("user_id") Long user_id, @PathVariable Long post_id, @RequestBody Comment comment){
        return ResponseEntity.ok().body(postService.commentPost(user_id,post_id,comment));
    }

    @PostMapping("/me/posts/{post_id}/likes")
    public ResponseEntity<?> likePost(@RequestHeader("user_id") Long user_id, @PathVariable Long post_id, @RequestBody Like like){
        return ResponseEntity.ok().body(postService.likePost(user_id,post_id,like));
    }
}

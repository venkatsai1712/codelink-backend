package com.venkatsai.codelink.controller;

import com.venkatsai.codelink.service.PostService;
import com.venkatsai.codelink.service.UserService;
import com.venkatsai.codelink.model.Comment;
import com.venkatsai.codelink.model.Like;
import com.venkatsai.codelink.model.Post;
import com.venkatsai.codelink.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @PostMapping("/get")
    public Long getUserId(@RequestBody User user){
        return userService.getUserId(user);
    }

    @GetMapping("/{id}/posts")
    public List<Post> getPosts(@PathVariable Long id){
        return userService.getPosts(id);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping("/{id}/posts")
    public Post createPost(@PathVariable Long id, @RequestBody Post post){
        log.info("Post Created");
        return userService.createPostByUserId(id,post);
    }

    @PostMapping("/{id}/follows/{following_id}")
    public User followUser(@PathVariable Long id, @PathVariable Long following_id){
        log.info("User Following");
        return userService.followUser(id,following_id);
    }

    @GetMapping("/{id}/followings")
    public List<User> getFollowings(@PathVariable Long id){
        return userService.getFollowings(id);
    }

    @GetMapping("/{id}/followers")
    public List<User> getFollowers(@PathVariable Long id){
        return userService.getFollowers(id);
    }

    @PostMapping("/{id}/posts/{post_id}/comments")
    public Comment commentPost(@PathVariable Long id, @PathVariable Long post_id,@RequestBody Comment comment){
        log.info("Post Commented");
        return postService.commentPost(id,post_id,comment);
    }

    @PostMapping("/{id}/posts/{post_id}/likes")
    public Like likePost(@PathVariable Long id, @PathVariable Long post_id, @RequestBody Like like){
        log.info("Post Liked");
        return postService.likePost(id,post_id,like);
    }
}

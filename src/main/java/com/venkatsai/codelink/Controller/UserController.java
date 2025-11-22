package com.venkatsai.codelink.Controller;

import com.venkatsai.codelink.Service.PostService;
import com.venkatsai.codelink.Service.UserService;
import com.venkatsai.codelink.model.Comment;
import com.venkatsai.codelink.model.Like;
import com.venkatsai.codelink.model.Post;
import com.venkatsai.codelink.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping("/{id}/posts")
    public Post createPost(@PathVariable Long id, @RequestBody Post post){
        return userService.createPostByUserId(id,post);
    }

    @PostMapping("/{id}/follows/{following_id}")
    public User followUser(@PathVariable Long id, @PathVariable Long following_id){
        return userService.followUser(id,following_id);
    }

    @GetMapping("/{id}/followings")
    public List<User> getFollowings(@PathVariable Long id){
        return userService.getFollowings(id);
    }

    @GetMapping("{id}/followers")
    public List<User> getFollowers(@PathVariable Long id){
        return userService.getFollowers(id);
    }

    @PostMapping("{id}/posts/{post_id}/comments")
    public Comment commentPost(@PathVariable Long id, @PathVariable Long post_id,@RequestBody Comment comment){
        return postService.commentPost(id,post_id,comment);
    }

    @PostMapping("{id}/posts/{post_id}/likes")
    public Like likePost(@PathVariable Long id, @PathVariable Long post_id, @RequestBody Like like){
        return postService.likePost(id,post_id,like);
    }
}

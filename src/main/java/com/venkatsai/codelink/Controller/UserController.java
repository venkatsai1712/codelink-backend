package com.venkatsai.codelink.Controller;

import com.venkatsai.codelink.Service.UserService;
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
}

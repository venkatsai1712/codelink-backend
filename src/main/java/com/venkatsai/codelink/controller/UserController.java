package com.venkatsai.codelink.controller;

import com.venkatsai.codelink.service.UserService;
import com.venkatsai.codelink.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public ResponseEntity<?> getUserProfile(@RequestHeader("user_id") Long user_id) {
        return ResponseEntity.ok().body(userService.getUserProfile(user_id));
    }

    @PostMapping("/me/follows/{following_id}")
    public ResponseEntity<?> followUser(@RequestHeader("user_id") Long user_id, @PathVariable Long following_id){
        String response = userService.followUser(user_id,following_id);
        if(response==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/me/followings")
    public ResponseEntity<?> getFollowings(@RequestHeader("user_id") Long user_id){
        return ResponseEntity.ok().body(userService.getFollowings(user_id));
    }

    @GetMapping("/me/followers")
    public ResponseEntity<?> getFollowers(@RequestHeader("user_id") Long user_id){
        return ResponseEntity.ok().body(userService.getFollowers(user_id));
    }

}

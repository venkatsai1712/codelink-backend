package com.venkatsai.codelink.Controller;

import com.venkatsai.codelink.Service.PostService;
import com.venkatsai.codelink.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin("*")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping
    public Post createUser(@RequestBody Post post){
        return postService.createPost(post);
    }

}

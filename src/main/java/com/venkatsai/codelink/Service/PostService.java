package com.venkatsai.codelink.Service;

import com.venkatsai.codelink.model.Post;
import com.venkatsai.codelink.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post createPost(Post post){
        return postRepository.save(post);
    }
}

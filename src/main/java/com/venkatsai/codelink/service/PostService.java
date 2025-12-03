package com.venkatsai.codelink.service;

import com.venkatsai.codelink.model.Comment;
import com.venkatsai.codelink.model.Like;
import com.venkatsai.codelink.model.Post;
import com.venkatsai.codelink.model.User;
import com.venkatsai.codelink.repositories.CommentRepository;
import com.venkatsai.codelink.repositories.LikeRepository;
import com.venkatsai.codelink.repositories.PostRepository;
import com.venkatsai.codelink.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private LikeRepository likeRepository;

    public Post createPost(Long id, Post post){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            post.setUser(user.get());
            user.get().getPosts().add(post);
            userRepository.save(user.get());
            return post;
        }
        return null;
    }

    public List<Post> getPosts(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get().getPosts();
        }
        return null;
    }

    public Comment commentPost(Long id, Long postId, Comment comment) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            Optional<Post> post = postRepository.findById(postId);
            if(post.isPresent()){
                    comment.setPost(post.get());
                    commentRepository.save(comment);
                    postRepository.save(post.get());
                    userRepository.save(user.get());
                    return comment;
            }
        }
        return null;
    }

    public Like likePost(Long id, Long postId,Like like) {
        Optional<Post> post = postRepository.findById(postId);
        if(post.isPresent()){
            Optional<User> user = userRepository.findById(id);
            if(user.isPresent()){
                like.setPost(post.get());
                likeRepository.save(like);
                postRepository.save(post.get());
                userRepository.save(user.get());
                return like;
            }
        }
        return null;
    }
}

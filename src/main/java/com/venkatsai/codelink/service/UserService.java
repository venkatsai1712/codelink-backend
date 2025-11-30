package com.venkatsai.codelink.service;

import com.venkatsai.codelink.model.Post;
import com.venkatsai.codelink.model.User;
import com.venkatsai.codelink.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    public User createUser(User user){
        user.getPosts().forEach(post -> post.setUser(user));
        return userRepository.save(user);
    }

    public User getUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public Post createPostByUserId(Long id, Post post){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            post.setUser(user.get());
            user.get().getPosts().add(post);
            userRepository.save(user.get());
            return post;
        }
        return null;
    }

    public User followUser(Long id,Long following_id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            List<User> followings = user.get().getFollowings();
            Optional<User> following_user = userRepository.findById(following_id);
            if(following_user.isPresent()){
                if(followings.contains(following_user.get())){
                    return user.get();
                }
                followings.add(following_user.get());
                user.get().setFollowings(followings);
                userRepository.save(user.get());
                return user.get();
            }
        }
        return null;
    }

    public List<User> getFollowings(Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get().getFollowings();
        }
        return null;
    }

    public List<User> getFollowers(Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get().getFollowers();
        }
        return null;
    }

    public Long getUserId(User user) {
        Optional<User> user1 = userRepository.findByEmail(user.getEmail());
        if(user1.isPresent()){
            return user1.get().getId();
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
}

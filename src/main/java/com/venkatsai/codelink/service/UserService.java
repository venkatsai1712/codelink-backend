package com.venkatsai.codelink.service;

import com.venkatsai.codelink.dto.UserProfileResponseDTO;
import com.venkatsai.codelink.dto.UserResponseRegisterDTO;
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
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    public String followUser(Long id,Long following_id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            List<User> followings = user.get().getFollowings();
            Optional<User> following_user = userRepository.findById(following_id);
            if(following_user.isPresent()){
                if(followings.contains(following_user.get())){
                    return "Already Following";
                }
                followings.add(following_user.get());
                user.get().setFollowings(followings);
                userRepository.save(user.get());
                return "Following";
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

    public UserProfileResponseDTO getUserProfile(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            User tmpUser = user.get();
            return UserProfileResponseDTO.builder()
                    .firstName(tmpUser.getFirstName())
                    .lastName(tmpUser.getLastName())
                    .email(tmpUser.getEmail())
                    .bio(tmpUser.getBio())
                    .currentCompany(tmpUser.getCurrentCompany())
                    .currentRoleName(tmpUser.getCurrentRoleName())
                    .followerCount(tmpUser.getFollowerCount())
                    .followingCount(tmpUser.getFollowingCount())
                    .linkedinUrl(tmpUser.getLinkedinUrl())
                    .profilePicture(tmpUser.getProfilePicture())
                    .githubUrl(tmpUser.getGithubUrl())
                    .username(tmpUser.getUsername())
                    .build();
        }
        return null;
    }
}

package com.venkatsai.codelink.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String profilePicture;
    private String bio;
    private String githubUrl;
    private String linkedinUrl;
    private String currentRoleName;
    private String currentCompany;
    private Long followerCount;
    private Long followingCount;

    @ElementCollection
    private List<String> skills;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Project> projects = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "followers_and_followings",
    joinColumns = @JoinColumn(name="follower_id"),
    inverseJoinColumns = @JoinColumn(name="following_id"))
    private List<User> followings = new ArrayList<>();

    @ManyToMany(mappedBy = "followings",fetch=FetchType.LAZY)
    private List<User> followers = new ArrayList<>();
}

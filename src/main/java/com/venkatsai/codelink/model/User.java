package com.venkatsai.codelink.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String first_name;
    private String last_name;
    private String username;
    private String email;
    private String password;
    private String photo;
    private String location;
    private String phone_number;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "followers_and_following",
    joinColumns = @JoinColumn(name="follower_id"),
    inverseJoinColumns = @JoinColumn(name="following_id"))
    private List<User> followings = new ArrayList<>();

    @ManyToMany(mappedBy = "followings")
    @JsonIgnore
    private List<User> followers = new ArrayList<>();
}

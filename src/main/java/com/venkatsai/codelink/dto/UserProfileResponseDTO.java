package com.venkatsai.codelink.dto;

import jakarta.persistence.ElementCollection;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileResponseDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String profilePicture;
    private String bio;
    private String githubUrl;
    private String linkedinUrl;
    private String currentRoleName;
    private String currentCompany;
    private Long followerCount;
    private Long followingCount;
}

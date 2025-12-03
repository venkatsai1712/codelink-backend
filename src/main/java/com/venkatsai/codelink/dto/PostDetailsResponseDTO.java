package com.venkatsai.codelink.dto;

import com.venkatsai.codelink.model.Comment;
import com.venkatsai.codelink.model.Like;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDetailsResponseDTO {
    private long id;
    private String content;
    private String photo;
    private String firstName;
    private String lastName;
    private String username;
    private String profilePicture;
    private List<Like> likes;
    private List<Comment> comments;
}

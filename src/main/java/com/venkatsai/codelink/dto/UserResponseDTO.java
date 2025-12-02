package com.venkatsai.codelink.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
}

package com.venkatsai.codelink.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestRegisterDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
}

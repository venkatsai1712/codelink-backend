package com.venkatsai.codelink.controller;

import com.venkatsai.codelink.configuration.SecurityConfig;
import com.venkatsai.codelink.dto.UserRequestRegisterDTO;
import com.venkatsai.codelink.dto.UserResponseRegisterDTO;
import com.venkatsai.codelink.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private SecurityConfig securityConfig;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRequestRegisterDTO user){
        UserResponseRegisterDTO response = authService.registerUser(user);
        if(response == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserRequestRegisterDTO user){
        UserResponseRegisterDTO response = authService.loginUser(user);
        if(response == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

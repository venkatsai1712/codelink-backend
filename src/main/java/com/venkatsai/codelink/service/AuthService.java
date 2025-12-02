package com.venkatsai.codelink.service;

import com.venkatsai.codelink.dto.UserRequestDTO;
import com.venkatsai.codelink.dto.UserResponseDTO;
import com.venkatsai.codelink.model.User;
import com.venkatsai.codelink.repositories.AuthRepository;
import com.venkatsai.codelink.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    public UserResponseDTO registerUser(UserRequestDTO userDto){
        User user = User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();

        Optional<User> optionalUserByUsername = userRepository.findByUsername(user.getUsername());
        Optional<User> optionalUserByEmail = userRepository.findByEmail(user.getEmail());

        if(optionalUserByUsername.isPresent())
            return null;
        else if(optionalUserByEmail.isPresent())
            return null;

        User resUser = userRepository.save(user);
        return UserResponseDTO.builder()
                    .firstName(resUser.getFirstName())
                    .lastName(resUser.getLastName())
                    .username(resUser.getUsername())
                    .email(resUser.getEmail())
                    .build();

    }

    public UserResponseDTO loginUser(UserRequestDTO userDto) {
        User user = User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();

        Optional<User> optionalUserByEmail = userRepository.findByEmail(user.getEmail());

        if(!optionalUserByEmail.isPresent())
            return null;

        User opUser =  optionalUserByEmail.get();

        if(opUser.getEmail().equals(user.getEmail()) &&
                opUser.getPassword().equals(user.getPassword()))
            return UserResponseDTO.builder()
                    .firstName(opUser.getFirstName())
                    .lastName(opUser.getLastName())
                    .username(opUser.getUsername())
                    .email(opUser.getEmail())
                    .build();
        return null;
    }
}

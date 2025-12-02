package com.venkatsai.codelink;

import com.venkatsai.codelink.configuration.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CodelinkApplication {
	public static void main(String[] args) {
        SpringApplication.run(CodelinkApplication.class, args);}

}

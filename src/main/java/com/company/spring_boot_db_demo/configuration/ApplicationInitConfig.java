package com.company.spring_boot_db_demo.configuration;

import com.company.spring_boot_db_demo.dto.request.UserCreationRequest;
import com.company.spring_boot_db_demo.entity.User;
import com.company.spring_boot_db_demo.enums.Role;
import com.company.spring_boot_db_demo.repository.UserRepository;
import com.company.spring_boot_db_demo.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

@Configuration
public class ApplicationInitConfig {

    @Value("${admin.username}")
    private String username;

    @Value("${admin.password}")
    private String password;


    @Bean
    ApplicationRunner applicationRunner(UserService userService, UserRepository userRepository){
        return args -> {
            if(userRepository.existsByUsername(username)){
                return;
            }
            UserCreationRequest userCreationRequest = UserCreationRequest.builder()
                    .username(username)
                    .password(password)
                    .roles(new HashSet<>(Arrays.asList(Role.ADMIN.name())))
                    .build();
            userService.createUser(userCreationRequest);
        };
    }
}

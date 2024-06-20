package com.company.spring_boot_db_demo.service;

import com.company.spring_boot_db_demo.dto.request.UserCreationRequest;
import com.company.spring_boot_db_demo.dto.request.UserUpdateRequest;
import com.company.spring_boot_db_demo.dto.response.UserResponse;
import com.company.spring_boot_db_demo.entity.User;
import com.company.spring_boot_db_demo.enums.Role;
import com.company.spring_boot_db_demo.exception.AppException;
import com.company.spring_boot_db_demo.exception.ErrorCode;
import com.company.spring_boot_db_demo.mapper.UserMapper;
import com.company.spring_boot_db_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserResponse createUser(UserCreationRequest request) {
        if(userRepository.existsByUsername(request.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(request);

        Set<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public List<User> getUser(){
        return userRepository.findAll();
    }

    public UserResponse getUser(String ID){
        return userMapper.toUserResponse(userRepository.findById(ID).orElseThrow(() -> new RuntimeException("User not found")));
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateUser(user,request);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }
}

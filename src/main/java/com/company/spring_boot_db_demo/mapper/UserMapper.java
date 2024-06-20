package com.company.spring_boot_db_demo.mapper;

import com.company.spring_boot_db_demo.dto.request.UserCreationRequest;
import com.company.spring_boot_db_demo.dto.request.UserUpdateRequest;
import com.company.spring_boot_db_demo.dto.response.UserResponse;
import com.company.spring_boot_db_demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest userCreationRequest);
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest userUpdateRequest);
}

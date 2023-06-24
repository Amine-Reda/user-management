package com.project.usermanagement.mapper;

import com.project.usermanagement.dao.entity.User;
import com.project.usermanagement.dto.PostUserDto;
import com.project.usermanagement.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    PostUserDto userToPostUserDto(User user);
    UserDto userToUserDto(User user);
    List<UserDto> usersToUserDtos(List<User> user);
}

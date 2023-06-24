package com.project.usermanagement.mapper;

import com.project.usermanagement.dao.entity.Right;
import com.project.usermanagement.dao.entity.User;
import com.project.usermanagement.dto.PostUserDto;
import com.project.usermanagement.dto.PutUserDto;
import com.project.usermanagement.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring",uses = {RightMapper.class})
public interface UserMapper {

    @Mapping(source = "rights", target = "rights", qualifiedByName = "rightToRightId")
    PostUserDto userToPostUserDto(User user);

    @Named("rightToRightId")
    public static Long rightToRightId(Right right) {
        return right.getId();
    }

    UserDto userToUserDto(User user);
    List<UserDto> usersToUserDtos(List<User> user);

    @Mapping(source = "rights", target = "rights", qualifiedByName = "rightToRightId")
    PutUserDto userToPutUserDto(User user);

}

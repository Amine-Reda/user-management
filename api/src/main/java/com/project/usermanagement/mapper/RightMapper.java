package com.project.usermanagement.mapper;

import com.project.usermanagement.dao.entity.Right;
import com.project.usermanagement.dto.PostRightDto;
import com.project.usermanagement.dto.RightDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RightMapper {
    PostRightDto rightToPostRightDto(Right right);

    RightDto  rightToRightDto(Right right);

    List<RightDto> rightsToRightDtos(List<Right> rights);
}

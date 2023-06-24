package com.project.usermanagement.dto;

import com.project.usermanagement.dao.entity.Right;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDto {

    private String sub;

    private String firstName;

    private String lastName;

    private String email;

    private List<RightDto> rights;
}

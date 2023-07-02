package com.project.usermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PutUserDto {

    private String firstName;


    private String lastName;

    @Email
    private String email;


}

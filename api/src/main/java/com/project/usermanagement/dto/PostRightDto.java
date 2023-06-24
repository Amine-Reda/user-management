package com.project.usermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PostRightDto {

    @NotNull
    private String name;

    @NotNull
    private String label;

    @NotNull
    private String description;
}

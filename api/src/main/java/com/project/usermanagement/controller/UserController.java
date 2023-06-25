package com.project.usermanagement.controller;

import com.project.usermanagement.SwaggerConfig;
import com.project.usermanagement.dao.exception.CustomizedException;
import com.project.usermanagement.dao.exception.User.UserNotFoundException;
import com.project.usermanagement.dao.service.UserService;
import com.project.usermanagement.dto.PostUserDto;
import com.project.usermanagement.dto.PutUserDto;
import com.project.usermanagement.dto.UserDto;
import com.project.usermanagement.mapper.UserMapper;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("/users")
@Slf4j
@Api(tags = {SwaggerConfig.USER_CONTROLLER_TAG})
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final UserMapper userMapper;


    @ApiOperation(value = "Gat All user",
            responseContainer = "List User")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return list of users"),
    })
    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok()
                .body(userMapper.usersToUserDtos(userService.getAllUsers()));
    }


    @ApiOperation(value = "Create user",
            responseContainer = "User")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return user"),
            @ApiResponse(code = 500, message = "Email already exist !"),
    })
    @PostMapping("")
    public ResponseEntity<PostUserDto> createUser(
            @ApiParam(name = "createUserDto", type = "PostUserDto", value = "This object contains the data to create " +
                    "user", required = true)
            @RequestBody @Valid PostUserDto createUserDto) throws CustomizedException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.userToPostUserDto(userService.createUser(createUserDto)));
    }

    @ApiOperation(value = "Update user",
            responseContainer = "User")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return user"),
            @ApiResponse(code = 404, message = "User not found !"),
    })
    @PutMapping("/{userId}")
    public ResponseEntity<PutUserDto> updateUser(
        @ApiParam(name = "updateUserDto", type = "PutUserDto", value = "This object contains the data to update user",
                required = true)
        @RequestBody @Valid PutUserDto updateUserDto,
        @ApiParam(name = "userId", type = "Long", value = "The id of user", example = "1", required = true)
        @PathVariable Long userId) throws UserNotFoundException {
        return ResponseEntity.ok()
                .body(userMapper.userToPutUserDto(userService.updateUser(userId,updateUserDto)));
    }


    @ApiOperation(value = "Delete user",
            responseContainer = "Boolean")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return true"),
            @ApiResponse(code = 400, message = "User not found !"),
    })
    @DeleteMapping ("/{userId}")
    public ResponseEntity<Boolean> deleteUser(
            @ApiParam(name = "userId", type = "Long", value = "The id of user", example = "1", required = true)
            @PathVariable Long userId) throws UserNotFoundException {
        return ResponseEntity.ok()
                .body(userService.deleteUser(userId));
    }
}

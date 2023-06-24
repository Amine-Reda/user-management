package com.project.usermanagement.controller;

import com.project.usermanagement.dao.exception.CustomizedException;
import com.project.usermanagement.dao.exception.User.UserNotFoundException;
import com.project.usermanagement.dao.service.UserService;
import com.project.usermanagement.dto.PostUserDto;
import com.project.usermanagement.dto.UserDto;
import com.project.usermanagement.mapper.UserMapper;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("/user")
@Slf4j
@Api(tags = {"User api resource"})
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final UserMapper userMapper;


    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok()
                .body(userMapper.usersToUserDtos(userService.getAllUsers()));
    }

    @PostMapping("")
    public ResponseEntity<PostUserDto> createUser(@RequestBody @Valid PostUserDto createUserDto) throws CustomizedException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.userToPostUserDto(this.userService.createUser(createUserDto)));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<PostUserDto> updateUser(@RequestBody @Valid PostUserDto updateUserDto,
            @PathVariable Long userId) throws UserNotFoundException {
        return ResponseEntity.ok()
                .body(userMapper.userToPostUserDto(this.userService.updateUser(userId,updateUserDto)));
    }

    @DeleteMapping ("/{userId}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long userId) throws UserNotFoundException {
        return ResponseEntity.ok()
                .body(userService.deleteUser(userId));
    }
}

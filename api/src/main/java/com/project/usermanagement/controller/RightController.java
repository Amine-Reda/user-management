package com.project.usermanagement.controller;

import com.project.usermanagement.SwaggerConfig;
import com.project.usermanagement.dao.exception.CustomizedException;
import com.project.usermanagement.dao.exception.Right.RightNotFoundException;
import com.project.usermanagement.dao.service.RightService;
import com.project.usermanagement.dto.PostRightDto;
import com.project.usermanagement.dto.RightDto;
import com.project.usermanagement.mapper.RightMapper;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("/right")
@Slf4j
@Api(tags = {SwaggerConfig.RIGHT_CONTROLLER_TAG})
@RequiredArgsConstructor
public class RightController {

    private final RightService rightService;

    private final RightMapper rightMapper;


    @ApiOperation(value = "Gat All rights",
            responseContainer = "List Right")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return list of rights"),
    })
    @GetMapping("")
    public ResponseEntity<List<RightDto>> getAllRights(){
        return ResponseEntity.ok()
                .body(rightMapper.rightsToRightDtos(rightService.getAllRights()));
    }

    @ApiOperation(value = "Create Right",
            responseContainer = "Right")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return Right"),
    })
    @PostMapping("")
    public ResponseEntity<PostRightDto> createRight(
            @ApiParam(name = "createRightDto", type = "PostRightDto", value = "This object contains the data to" +
                    " create right", required = true)
            @RequestBody @Valid PostRightDto createRightDto) throws CustomizedException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(rightMapper.rightToPostRightDto(rightService.createRight(createRightDto)));
    }

    @ApiOperation(value = "Delete right",
            responseContainer = "Boolean")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return true"),
            @ApiResponse(code = 404, message = "Right not found !")
    })
    @DeleteMapping ("/{rightId}")
    public ResponseEntity<Boolean> deleteRight(
            @ApiParam(name = "rightId", type = "Long", value = "The id of Right", example = "4", required = true)
            @PathVariable Long rightId) throws RightNotFoundException {
        return ResponseEntity.ok()
                .body(rightService.deleteRight(rightId));
    }


}

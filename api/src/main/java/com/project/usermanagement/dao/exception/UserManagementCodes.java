package com.project.usermanagement.dao.exception;

import lombok.Getter;

@Getter
public enum UserManagementCodes {
    // User not found
    USER_NOT_FOUND("User not found", 1),
    RIGHT_NOT_FOUND("Right not found", 2);

    private final String message;
    private final Integer code;

    UserManagementCodes(String message, Integer code) {
        this.message = message;
        this.code = code;
    }
}

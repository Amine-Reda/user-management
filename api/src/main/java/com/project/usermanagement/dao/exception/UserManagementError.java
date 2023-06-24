package com.project.usermanagement.dao.exception;

import lombok.Builder;

@Builder
public class UserManagementError {
    private Integer code;
    private String message;

    public UserManagementError() {
    }

    public UserManagementError(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package com.project.usermanagement.dao.exception;

public class CustomizedException extends UserManagementException{
    public CustomizedException(String message) {
        super(message, 400);
    }
}

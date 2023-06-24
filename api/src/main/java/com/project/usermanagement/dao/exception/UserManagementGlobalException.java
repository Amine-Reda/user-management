package com.project.usermanagement.dao.exception;

public class UserManagementGlobalException extends UserManagementException{

    public UserManagementGlobalException(UserManagementCodes userManagementCodes) {
        super(userManagementCodes.getMessage(), userManagementCodes.getCode());
    }
}

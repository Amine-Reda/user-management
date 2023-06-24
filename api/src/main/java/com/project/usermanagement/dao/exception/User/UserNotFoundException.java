package com.project.usermanagement.dao.exception.User;

import com.project.usermanagement.dao.exception.UserManagementCodes;
import com.project.usermanagement.dao.exception.UserManagementException;

public class UserNotFoundException extends UserManagementException {
    public UserNotFoundException(UserManagementCodes userManagementCodes) {
        super(userManagementCodes.getMessage(), userManagementCodes.getCode());
    }
}

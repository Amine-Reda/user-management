package com.project.usermanagement.dao.exception.Right;

import com.project.usermanagement.dao.exception.UserManagementCodes;
import com.project.usermanagement.dao.exception.UserManagementException;

public class RightNotFoundException extends UserManagementException {

    public RightNotFoundException(UserManagementCodes userManagementCodes) {
        super(userManagementCodes.getMessage(), userManagementCodes.getCode());
    }
}

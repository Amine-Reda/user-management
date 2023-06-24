package com.project.usermanagement.dao.exception;

import lombok.Getter;

@Getter
public class UserManagementException  extends Exception{

    private Integer code;

    public UserManagementException(String message,Integer code){
        super(message);
        this.code=code;
    }
}

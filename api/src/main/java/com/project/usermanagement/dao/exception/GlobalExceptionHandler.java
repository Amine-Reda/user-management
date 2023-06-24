package com.project.usermanagement.dao.exception;


import com.project.usermanagement.dao.exception.User.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice()
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

   @ExceptionHandler({
           UserNotFoundException.class,
   })
   protected ResponseEntity<UserManagementError> handleNotFoundException(UserManagementException userManagementException) {
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
               UserManagementError.builder()
                       .code(userManagementException.getCode())
                       .message(userManagementException.getMessage())
                       .build()
       );
   }

}

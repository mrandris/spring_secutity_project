package com.sda.springsecurity.config;

import com.sda.springsecurity.service.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    /** catch generic exception **/
/*    @ExceptionHandler(value = Exception.class)
    public ResponseEntity general() {
        return new ResponseEntity("ERROR", HttpStatus.CONFLICT);
    }*/

    /** for specific exceptions **/
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity general(RuntimeException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}

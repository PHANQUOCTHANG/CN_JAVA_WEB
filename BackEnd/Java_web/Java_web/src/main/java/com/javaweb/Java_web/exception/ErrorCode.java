package com.javaweb.Java_web.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    LOGIN_NAME_EXISTS(1001 , "Login name is exists" , HttpStatus.BAD_REQUEST) ,
    LOGIN_NO_SUCCESS(1002 , "Login no success" , HttpStatus.BAD_REQUEST) ,
    UNAUTHORIZED(1003,"You is not have permission" , HttpStatus.UNAUTHORIZED)
    ;
    private int code ;
    private String message ;
    private HttpStatusCode statusCode ;

    ErrorCode(int code, String message , HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode ;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

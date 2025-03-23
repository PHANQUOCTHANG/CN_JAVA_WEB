package com.javaweb.Java_web.exception;

import com.javaweb.Java_web.dto.response.ApiResponse;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;


@ControllerAdvice
@Builder
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse> handlingRunTimeException (RuntimeException runtimeException) {
        ApiResponse apiResponse = new ApiResponse() ;
        apiResponse.setCode(1001);
        apiResponse.setMessage(runtimeException.getMessage());
        return ResponseEntity.badRequest().body(apiResponse) ;
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAccessDeniedException (AppException appException) {
        ErrorCode errorCode = appException.getErrorCode() ;
        ApiResponse apiResponse = new ApiResponse() ;
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
        return ResponseEntity.status(errorCode.getStatusCode()).body(apiResponse) ;
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse> handlingAppException (AccessDeniedException exception) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
        ApiResponse apiResponse = new ApiResponse() ;
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
        return ResponseEntity.status(errorCode.getStatusCode()).body(apiResponse) ;
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<String> handlingMethodArgumentNotValidException (MethodArgumentNotValidException methodArgumentNotValidException) {
        return ResponseEntity.badRequest().body(methodArgumentNotValidException.getFieldError().getDefaultMessage()) ;
    }
}

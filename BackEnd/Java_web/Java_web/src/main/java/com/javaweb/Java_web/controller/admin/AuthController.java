package com.javaweb.Java_web.controller.admin;

import com.javaweb.Java_web.dto.request.AuthRequest;
import com.javaweb.Java_web.dto.request.IntrospectRequest;
import com.javaweb.Java_web.dto.response.ApiResponse;
import com.javaweb.Java_web.dto.response.AuthResponse;
import com.javaweb.Java_web.dto.response.IntrospectResponse;
import com.javaweb.Java_web.service.AuthService;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;


@RestController
@RequestMapping("/admin/auth")
public class AuthController {

    @Autowired
    private AuthService authService ;


    @PostMapping("/login")
    public ApiResponse<AuthResponse> loginPost(@RequestBody AuthRequest authRequest) {
        ApiResponse<AuthResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(authService.login(authRequest));
        return apiResponse ;
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> login(@RequestBody IntrospectRequest introspectRequest) throws ParseException, JOSEException {
        IntrospectResponse result = authService.introspect(introspectRequest) ;
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build() ;
    }

}

package com.javaweb.Java_web.dto.response;


import lombok.Builder;
import lombok.Data;

@Builder
public class AuthResponse {

    private String token ;
    private boolean authenticated ;

    public AuthResponse() {
    }

    public AuthResponse(String token, boolean authenticated) {
        this.token = token;
        this.authenticated = authenticated;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

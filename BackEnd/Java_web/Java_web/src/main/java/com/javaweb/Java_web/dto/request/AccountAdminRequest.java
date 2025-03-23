package com.javaweb.Java_web.dto.request;

import java.util.Set;

public class AccountAdminRequest {
    private String loginName ;
    private String password ;

    public AccountAdminRequest() {
    }

    public AccountAdminRequest(String loginName, String password) {
        this.loginName = loginName;
        this.password = password;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

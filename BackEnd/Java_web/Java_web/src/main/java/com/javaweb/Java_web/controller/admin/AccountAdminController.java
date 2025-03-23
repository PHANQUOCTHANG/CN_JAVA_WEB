package com.javaweb.Java_web.controller.admin;

import com.javaweb.Java_web.dto.request.AccountAdminRequest;
import com.javaweb.Java_web.dto.response.AccountAdminResponse;
import com.javaweb.Java_web.dto.response.ApiResponse;
import com.javaweb.Java_web.entity.AccountAdmin;
import com.javaweb.Java_web.service.AccountAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/account")
public class AccountAdminController {

    @Autowired
    private AccountAdminService accountAdminService ;

    @GetMapping
    public List<AccountAdmin> listAccountAdmin () {
        return accountAdminService.listAccountAdmin() ;
    }

    @PostMapping("/create")
    public ApiResponse<AccountAdminResponse> createAccountAdminPost(@RequestBody AccountAdminRequest accountAdminRequest) {
        ApiResponse<AccountAdminResponse> apiResponse = new ApiResponse<>() ;
        apiResponse.setResult(accountAdminService.createAccountAdminPost(accountAdminRequest));
        return apiResponse ;
    }

    @GetMapping("/myInfo")
    public ApiResponse<AccountAdminResponse> myInfo() {
        return ApiResponse.<AccountAdminResponse>builder()
                .result(accountAdminService.getMyInfo())
                .build() ;
    }

}

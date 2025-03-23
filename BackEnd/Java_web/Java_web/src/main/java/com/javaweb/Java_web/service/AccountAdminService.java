package com.javaweb.Java_web.service;

import com.javaweb.Java_web.dto.request.AccountAdminRequest;
import com.javaweb.Java_web.dto.response.AccountAdminResponse;
import com.javaweb.Java_web.entity.AccountAdmin;
import com.javaweb.Java_web.enums.Role;
import com.javaweb.Java_web.exception.AppException;
import com.javaweb.Java_web.exception.ErrorCode;
import com.javaweb.Java_web.repository.AccountAdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;

@Service
@Slf4j
public class AccountAdminService {

    @Autowired
    private AccountAdminRepository accountAdminRepository ;

    @Autowired
    private PasswordEncoder passwordEncoder ;
    //[GET] get all accountAdmin .


    @PreAuthorize("hasRole('QUAN_TRI_VIEN')")
    public List<AccountAdmin> listAccountAdmin() {
        log.info("In method list account admin");
        return accountAdminRepository.findAllByDeletedFalse() ;
    }

    public AccountAdminResponse getMyInfo() {
        var context = SecurityContextHolder.getContext() ;
        String name = context.getAuthentication().getName() ;

        AccountAdmin accountAdmin = accountAdminRepository.findByLoginNameAndDeletedFalse(name).orElseThrow(
                () -> new AppException(ErrorCode.LOGIN_NO_SUCCESS)
        ) ;
        AccountAdminResponse accountAdminResponse = new AccountAdminResponse(accountAdmin.getAccountId(), accountAdmin.getLoginName() , accountAdmin.getRoles() , accountAdmin.getDeleted()) ;
        return accountAdminResponse ;
    }

    // [POST]
    @PreAuthorize("hasRole('QUAN_TRI_VIEN')")
    public AccountAdminResponse createAccountAdminPost(AccountAdminRequest accountAdminRequest){
        if (accountAdminRepository.existsByLoginName(accountAdminRequest.getLoginName()))
            throw new AppException(ErrorCode.LOGIN_NAME_EXISTS) ;

        AccountAdmin accountAdmin = new AccountAdmin() ;
        accountAdmin.setLoginName(accountAdminRequest.getLoginName());
        accountAdmin.setPassword(passwordEncoder.encode(accountAdminRequest.getPassword()));

        HashSet<String> roles = new HashSet<>() ;
        roles.add(Role.NHAN_VIEN.name()) ;
        accountAdmin.setRoles(roles);
        accountAdminRepository.save(accountAdmin) ;

        AccountAdminResponse accountAdminResponse = new AccountAdminResponse() ;
        accountAdminResponse.setAccountId(accountAdmin.getAccountId());
        accountAdminResponse.setLoginName(accountAdmin.getLoginName());
        accountAdminResponse.setRoles(accountAdmin.getRoles());
        return accountAdminResponse ;
    }
}

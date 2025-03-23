package com.javaweb.Java_web.config;

import com.javaweb.Java_web.dto.response.AccountAdminResponse;
import com.javaweb.Java_web.entity.AccountAdmin;
import com.javaweb.Java_web.enums.Role;
import com.javaweb.Java_web.repository.AccountAdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@Slf4j
public class ApplicationConfig {

    @Autowired
    PasswordEncoder passwordEncoder ;

    @Bean
    ApplicationRunner applicationRunner(AccountAdminRepository accountAdminRepository)
    {
        return args -> {
            if(accountAdminRepository.findByLoginNameAndDeletedFalse("admin").isEmpty()) {
                var roles = new HashSet<String>() ;
                roles.add(Role.QUAN_TRI_VIEN.name()) ;

                AccountAdmin accountAdmin = AccountAdmin.builder()
                        .loginName("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles(roles)
                        .deleted(false)
                        .build();

                accountAdminRepository.save(accountAdmin) ;
                log.warn("admin account has been created with default password: admin");
            }

        } ;
    }
}

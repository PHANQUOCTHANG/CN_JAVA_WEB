package com.javaweb.Java_web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountAdminResponse {
    private Long accountId ;
    private String loginName ;
    private Set<String> roles ;
    private Boolean deleted = false ;
}

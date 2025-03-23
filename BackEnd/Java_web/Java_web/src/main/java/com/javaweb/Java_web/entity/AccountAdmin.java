package com.javaweb.Java_web.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class AccountAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId ;
    private String loginName ;
    private String password ;
    private Set<String> roles ;
    private Boolean deleted = false ;

}

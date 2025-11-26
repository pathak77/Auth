package com.prod.auth.Dto;

import com.prod.auth.Entity.Authority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoginRequestDto {

    private String username;
    private String password;
    List<Authority> authorityList;
}

package com.prod.auth.Dto;

import com.prod.auth.Entity.Authority;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LoginRequestDto {

    private String username;
    private String password;
    List<Authority> authorityList;
}

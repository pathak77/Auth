package com.prod.auth.Dto;

import lombok.Data;

@Data
public class SigninRequestDto {

    private String username;
    private String email;
    private String password;
}

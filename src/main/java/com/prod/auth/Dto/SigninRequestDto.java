package com.prod.auth.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SigninRequestDto {

    private String username;
    private String email;
    private String password;
}

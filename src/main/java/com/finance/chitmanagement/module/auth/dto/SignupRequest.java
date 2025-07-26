package com.finance.chitmanagement.module.auth.dto;

import lombok.Data;
//TODO: can also add mobile, confirmPassword, etc., in future.
@Data
public class SignupRequest {
    private String username;
    private String password;
    private String email;
}

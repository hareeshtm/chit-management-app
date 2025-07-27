package com.finance.chitmanagement.module.auth.service;

import com.finance.chitmanagement.module.auth.dto.LoginRequest;
import com.finance.chitmanagement.module.auth.dto.LoginResponse;
import com.finance.chitmanagement.module.auth.dto.SignupRequest;

public interface AuthService {
    void registerCustomer(SignupRequest request);
    LoginResponse login(LoginRequest request);
}

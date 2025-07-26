package com.finance.chitmanagement.module.auth.service;

import com.finance.chitmanagement.module.auth.dto.SignupRequest;

public interface AuthService {
    void registerCustomer(SignupRequest request);
}

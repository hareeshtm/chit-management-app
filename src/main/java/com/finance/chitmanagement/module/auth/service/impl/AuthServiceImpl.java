package com.finance.chitmanagement.module.auth.service.impl;

import com.finance.chitmanagement.module.auth.dto.SignupRequest;
import com.finance.chitmanagement.module.auth.entity.Role;
import com.finance.chitmanagement.module.auth.entity.User;
import com.finance.chitmanagement.module.auth.repository.RoleRepository;
import com.finance.chitmanagement.module.auth.repository.UserRepository;
import com.finance.chitmanagement.module.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registerCustomer(SignupRequest request) {
        if(userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        User customer = new User();
        customer.setUsername(request.getUsername());
        customer.setPassword(passwordEncoder.encode(request.getPassword()));
        customer.setEmail(request.getEmail());

        Role customerRole = roleRepository.findByName("ROLE_CUSTOMER")
                .orElseThrow(() -> new RuntimeException("Role not found: ROLE_CUSTOMER"));
        customer.setRoles(Set.of(customerRole));
        userRepository.save(customer);
    }
}

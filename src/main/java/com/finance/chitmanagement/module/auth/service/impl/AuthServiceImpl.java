package com.finance.chitmanagement.module.auth.service.impl;

import com.finance.chitmanagement.common.util.JwtUtil;
import com.finance.chitmanagement.module.auth.dto.LoginRequest;
import com.finance.chitmanagement.module.auth.dto.LoginResponse;
import com.finance.chitmanagement.module.auth.dto.SignupRequest;
import com.finance.chitmanagement.module.auth.entity.Role;
import com.finance.chitmanagement.module.auth.entity.User;
import com.finance.chitmanagement.module.auth.repository.RoleRepository;
import com.finance.chitmanagement.module.auth.repository.UserRepository;
import com.finance.chitmanagement.module.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

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

    @Override
    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        /**
         * this line tells Spring Security: "The user is now authenticated"
         * This line stores the authenticated user's details in the Spring Security context so that:
         * 1.Spring Security knows the user is authenticated
         * 2.You can access the logged-in user details anywhere using:
         * Authentication auth = SecurityContextHolder.getContext().getAuthentication();
         *  Without this line?
         * 1.The user would not be considered authenticated by Spring Security.
         * 2.You would generate the JWT, but Spring wouldn't recognize the user in future requests.
         * 3.Authorization mechanisms relying on the SecurityContext would fail.
         */
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtil.generateToken(request.getUsername());
        return new LoginResponse(token);
    }
}

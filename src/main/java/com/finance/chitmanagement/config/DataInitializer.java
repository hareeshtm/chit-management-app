package com.finance.chitmanagement.config;

import com.finance.chitmanagement.module.auth.entity.Role;
import com.finance.chitmanagement.module.auth.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * This will run only once on app startup and insert roles if they don’t exist.
 * On every application startup, it checks if each role exists.
 * If not, it inserts the role.
 * Prevents duplication and ensures app always has the base roles.
 */

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        List<String> defaultRoles = List.of("ROLE_ADMIN", "ROLE_CUSTOMER");
        defaultRoles.forEach(
                roleName -> {
                    if (!roleRepository.existsByName(roleName)) {
                        Role role = new Role();
                        role.setName(roleName);
                        roleRepository.save(role);
                        System.out.println("Inserted role: " + roleName);
                    }
                }
        );
    }
}

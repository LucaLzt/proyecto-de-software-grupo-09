package com.unla.grupo9.steam.utils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.unla.grupo9.steam.entities.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.unla.grupo9.steam.entities.UserRole;
import com.unla.grupo9.steam.repositories.IUserRepository;
import com.unla.grupo9.steam.repositories.IUserRoleRepository;
import com.unla.grupo9.steam.utils.Roles;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class Runners implements CommandLineRunner {

    private final IUserRoleRepository rolRepository;
    private final IUserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        ensureRolesExist();

        // Create recommended users if they don't exist
        createUserIfNotExists("admin@test.com", "admin123", Roles.ROLE_ADMIN);
        createUserIfNotExists("user@test.com", "user123", Roles.ROLE_AUDITOR);

        // Backwards compatibility: keep a simple 'admin' user if no users exist at all
        if (userRepository.count() == 0) {
            List<UserRole> allRoles = rolRepository.findAll();
            User fallback = new User(
                    "admin",
                    passwordEncoder.encode("1111"),
                    true,
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    allRoles
            );
            userRepository.save(fallback);
        }
    }

    private void ensureRolesExist() {
        // Ensure ROLE_ADMIN exists
        Optional<UserRole> adminRole = rolRepository.findByRole(Roles.ROLE_ADMIN);
        if (adminRole.isEmpty()) {
            rolRepository.save(new UserRole(Roles.ROLE_ADMIN));
        }

        // Ensure ROLE_AUDITOR exists
        Optional<UserRole> auditorRole = rolRepository.findByRole(Roles.ROLE_AUDITOR);
        if (auditorRole.isEmpty()) {
            rolRepository.save(new UserRole(Roles.ROLE_AUDITOR));
        }
    }

    private void createUserIfNotExists(String username, String rawPassword, Roles role) {
        Optional<User> existing = userRepository.findByUsername(username);
        if (existing.isPresent()) {
            return;
        }

        UserRole assignedRole = rolRepository.findByRole(role).orElseGet(() -> rolRepository.save(new UserRole(role)));

        User newUser = new User(
                username,
                passwordEncoder.encode(rawPassword),
                true,
                LocalDateTime.now(),
                LocalDateTime.now(),
                List.of(assignedRole)
        );

        userRepository.save(newUser);
    }
}
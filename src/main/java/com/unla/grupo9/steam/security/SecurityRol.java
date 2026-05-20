package com.unla.grupo9.steam.security;

import com.unla.grupo9.steam.entities.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;

@AllArgsConstructor
public class SecurityRol implements GrantedAuthority {

    @Serial
    private static final long serialVersionUID = 1L;

    private UserRole userRole;

    @Override
    public String getAuthority() {
        return userRole.getRole().toString();
    }
}

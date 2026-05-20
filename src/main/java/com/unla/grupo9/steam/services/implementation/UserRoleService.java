package com.unla.grupo9.steam.services.implementation;

import com.unla.grupo9.steam.entities.UserRole;
import com.unla.grupo9.steam.repositories.IUserRoleRepository;
import com.unla.grupo9.steam.services.IUserRoleService;
import com.unla.grupo9.steam.utils.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRoleService implements IUserRoleService {

    private final IUserRoleRepository userRoleRepository;

    @Override
    public List<UserRole> listarRoles() {
        return userRoleRepository.findAll();
    }

    public Optional<UserRole> traerPorNombre(Roles rol) {
        return userRoleRepository.findByRole(rol);
    }
}
package com.unla.grupo9.steam.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.unla.grupo9.steam.entities.UserRole;
import com.unla.grupo9.steam.utils.Roles;


@Repository
public interface IUserRoleRepository extends JpaRepository<UserRole, Serializable> {
	
	Optional<UserRole> findByRole(Roles rol);
}
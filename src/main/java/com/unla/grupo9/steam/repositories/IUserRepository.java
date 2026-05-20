package com.unla.grupo9.steam.repositories;

import java.io.Serializable;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo9.steam.entities.User;


@Repository
public interface IUserRepository extends JpaRepository<User, Serializable> {
	
	Optional<User> findByUsername(String username);
	
}
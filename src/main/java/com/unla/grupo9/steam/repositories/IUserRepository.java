package com.unla.grupo9.steam.repositories;

import com.unla.grupo9.steam.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Serializable> {
    Optional<User> findByUsername(String username);
}
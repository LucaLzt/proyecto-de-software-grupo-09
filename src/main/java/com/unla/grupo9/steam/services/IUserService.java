package com.unla.grupo9.steam.services;

import com.unla.grupo9.steam.entities.User;

import java.util.Optional;

public interface IUserService {
    User saveOrUpdate(User user);
    Optional<User> findByUsername(String username);
}
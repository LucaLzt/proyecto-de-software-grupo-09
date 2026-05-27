package com.unla.grupo9.steam.services;

import com.unla.grupo9.steam.entities.User;

public interface IUserService {
    User saveOrUpdate(User user);

    User findByUsername(String username);
}

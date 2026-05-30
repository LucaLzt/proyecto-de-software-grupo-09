package com.unla.grupo9.steam.services.implementation;

import com.unla.grupo9.steam.entities.User;
import com.unla.grupo9.steam.repositories.IUserRepository;
import com.unla.grupo9.steam.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User saveOrUpdate(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
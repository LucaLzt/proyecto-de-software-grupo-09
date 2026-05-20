package com.unla.grupo9.steam.services.implementation;

import com.unla.grupo9.steam.entities.User;
import com.unla.grupo9.steam.repositories.IUserRepository;
import com.unla.grupo9.steam.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {

    private final IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        Optional<User> usuarioBuscado = userRepository.findByUsername(username);
        if (usuarioBuscado.isPresent()) {
            return new SecurityUser(usuarioBuscado.get());
        }
        throw new UsernameNotFoundException("el usuario no existe");
    }
}

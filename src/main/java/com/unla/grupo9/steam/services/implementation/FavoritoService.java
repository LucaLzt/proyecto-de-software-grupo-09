package com.unla.grupo9.steam.services.implementation;

import com.unla.grupo9.steam.entities.Juego;
import com.unla.grupo9.steam.entities.User;
import com.unla.grupo9.steam.repositories.IUserRepository;
import com.unla.grupo9.steam.services.IFavoritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavoritoService implements IFavoritoService {

    private final IUserRepository userRepository;

    @Override
    public void agregarFavorito(User user, Juego juego) {
        user.getFavoritos().add(juego);
        userRepository.save(user);
    }

    @Override
    public void eliminarFavorito(User user, Juego juego) {
        user.getFavoritos().remove(juego);
        userRepository.save(user);
    }

    @Override
    public boolean esFavorito(User user, Juego juego) {
        return user.getFavoritos().contains(juego);
    }
}
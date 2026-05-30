package com.unla.grupo9.steam.services;

import com.unla.grupo9.steam.entities.Juego;
import com.unla.grupo9.steam.entities.User;

public interface IFavoritoService {
    void agregarFavorito(User user, Juego juego);
    void eliminarFavorito(User user, Juego juego);
    boolean esFavorito(User user, Juego juego);
}
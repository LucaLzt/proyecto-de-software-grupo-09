package com.unla.grupo9.steam.services;

import com.unla.grupo9.steam.entities.Juego;
import java.util.List;

public interface IJuegoService {
    List<Juego> obtenerTodos();
    List<Juego> buscarPorNombre(String nombre);
    Juego obtenerPorId(Long id);
    List<Juego> buscarPorGenero(String genero);
    Juego guardar(Juego juego);
}
package com.unla.grupo9.steam.services;

import com.unla.grupo9.steam.entities.Carrito;
import com.unla.grupo9.steam.entities.Juego;

public interface ICarritoService {
    Carrito obtenerCarrito(int userId);
    void agregarAlCarrito(int userId, Juego juego);
    void eliminarDelCarrito(int userId, Long juegoId);
    void comprar(int userId);
}
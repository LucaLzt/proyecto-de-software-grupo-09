package com.unla.grupo9.steam.repositories;

import com.unla.grupo9.steam.entities.CarritoJuego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarritoJuegoRepository extends JpaRepository<CarritoJuego, Long> {
}
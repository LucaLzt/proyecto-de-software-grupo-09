package com.unla.grupo9.steam.repositories;

import com.unla.grupo9.steam.entities.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarritoRepository extends JpaRepository<Carrito, Long> {
    Carrito findByUserId(int userId);
}
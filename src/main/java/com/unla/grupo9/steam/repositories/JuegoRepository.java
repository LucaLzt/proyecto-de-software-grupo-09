package com.unla.grupo9.steam.repositories;

import com.unla.grupo9.steam.entities.Juego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JuegoRepository extends JpaRepository<Juego, Long> {
    List<Juego> findByNombreContainingIgnoreCase(String nombre);
    List<Juego> findByGenero(String genero);
}

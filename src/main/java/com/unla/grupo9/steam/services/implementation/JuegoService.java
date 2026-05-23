package com.unla.grupo9.steam.services.implementation;

import com.unla.grupo9.steam.entities.Juego;
import com.unla.grupo9.steam.services.IJuegoService;
import com.unla.grupo9.steam.repositories.IJuegoRepository;
import org.springframework.stereotype.Service;

@Service

public class JuegoService implements IJuegoService  {

    private final IJuegoRepository juegoRepository;

    public JuegoService(IJuegoRepository juegoRepository) {
        this.juegoRepository = juegoRepository;
    }

    @Override
    public Juego saveOrUpdate(Juego juego) {

        return juegoRepository.save(juego);

    }

}

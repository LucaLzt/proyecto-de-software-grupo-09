package com.unla.grupo9.steam.services.implementation;

import com.unla.grupo9.steam.entities.Juego;
import com.unla.grupo9.steam.repositories.JuegoRepository;
import com.unla.grupo9.steam.services.IJuegoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JuegoServiceImp implements IJuegoService {

    private final JuegoRepository juegoRepository;

    @Override
    public List<Juego> obtenerTodos() {
        return juegoRepository.findAll();
    }

    @Override
    public List<Juego> buscarPorNombre(String nombre) {
        return juegoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public Juego obtenerPorId(Long id) {
        return juegoRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Juego no encontrado"));
    }
    
    @Override
    public List<Juego> buscarPorGenero(String genero) {
        return juegoRepository.findByGenero(genero);
    }

    @Override
    public Juego guardar(Juego juego) {
        return juegoRepository.save(juego);
    }

    @Override
    public void eliminar(Long id) {
        juegoRepository.deleteById(id);
    }

}
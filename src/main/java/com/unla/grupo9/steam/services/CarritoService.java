package com.unla.grupo9.steam.services;

import com.unla.grupo9.steam.entities.Carrito;
import com.unla.grupo9.steam.entities.CarritoJuego;
import com.unla.grupo9.steam.entities.Juego;
import com.unla.grupo9.steam.entities.User;
import com.unla.grupo9.steam.repositories.ICarritoJuegoRepository;
import com.unla.grupo9.steam.repositories.ICarritoRepository;
import com.unla.grupo9.steam.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarritoService implements ICarritoService {

    @Autowired
    private ICarritoRepository carritoRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ICarritoJuegoRepository carritoJuegoRepository;

    @Override
    public Carrito obtenerCarrito(int userId) {
        Carrito carrito = carritoRepository.findByUserId(userId);
        if (carrito == null) {
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {
                carrito = new Carrito();
                carrito.setUser(user.get());
                carritoRepository.save(carrito);
            }
        }
        return carrito;
    }

    @Override
    public void agregarAlCarrito(int userId, Juego juego) {
        Carrito carrito = obtenerCarrito(userId);
        if (carrito != null) {
            boolean existe = carrito.getItems().stream().anyMatch(item -> item.getJuego().equals(juego));
            if (!existe) {
                CarritoJuego carritoJuego = new CarritoJuego();
                carritoJuego.setCarrito(carrito);
                carritoJuego.setJuego(juego);
                carrito.getItems().add(carritoJuego);
                actualizarTotal(carrito);
                carritoRepository.save(carrito);
            }
        }
    }

    @Override
    public void eliminarDelCarrito(int userId, Long juegoId) {
        Carrito carrito = obtenerCarrito(userId);
        if (carrito != null) {
            carrito.getItems().removeIf(item -> item.getJuego().getId().equals(juegoId));
            actualizarTotal(carrito);
            carritoRepository.save(carrito);
        }
    }

    @Override
    public void comprar(int userId) {
        Carrito carrito = obtenerCarrito(userId);
        if (carrito != null) {
            // Lógica de compra (por ejemplo, crear una orden, vaciar el carrito, etc.)
            carrito.getItems().clear();
            actualizarTotal(carrito);
            carritoRepository.save(carrito);
        }
    }

    private void actualizarTotal(Carrito carrito) {
        double total = carrito.getItems().stream()
                .mapToDouble(item -> item.getJuego().getPrecio())
                .sum();
        carrito.setTotal(total);
    }
}
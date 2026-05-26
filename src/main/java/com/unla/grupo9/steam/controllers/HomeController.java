package com.unla.grupo9.steam.controllers;

import com.unla.grupo9.steam.entities.Juego;
import com.unla.grupo9.steam.services.IJuegoService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {

    private final IJuegoService juegoService;

    @GetMapping("")
    public String home(Model model,
                       @RequestParam(name = "busqueda", required = false) String busqueda,
                       @RequestParam(name = "genero", required = false) String genero) {

        List<Juego> juegos;

        if (busqueda != null && !busqueda.isBlank()) {
            juegos = juegoService.buscarPorNombre(busqueda);
        } else if (genero != null && !genero.isBlank()) {
            juegos = juegoService.buscarPorGenero(genero);
        } else {
            juegos = juegoService.obtenerTodos();
        }

        model.addAttribute("juegos", juegos);
        model.addAttribute("busqueda", busqueda);
        model.addAttribute("generoSeleccionado", genero);
        return "home";
    }
}
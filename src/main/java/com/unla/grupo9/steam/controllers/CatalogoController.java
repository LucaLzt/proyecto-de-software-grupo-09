package com.unla.grupo9.steam.controllers;

import com.unla.grupo9.steam.services.IJuegoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/catalogo")
@RequiredArgsConstructor
public class CatalogoController {

    private final IJuegoService juegoService;

    @GetMapping
    public String mostrarCatalogo(Model model, @RequestParam(name = "busqueda", required = false) String busqueda) {
        var juegos = (busqueda != null && !busqueda.isBlank())
                ? juegoService.buscarPorNombre(busqueda)
                : juegoService.obtenerTodos();

        model.addAttribute("juegos", juegos);
        model.addAttribute("busqueda", busqueda);
        return "catalogo";
    }
}
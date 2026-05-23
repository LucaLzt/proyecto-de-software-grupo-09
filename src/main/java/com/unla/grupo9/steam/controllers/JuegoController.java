package com.unla.grupo9.steam.controllers;

import com.unla.grupo9.steam.entities.Juego;
import com.unla.grupo9.steam.services.IJuegoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/juego")
public class JuegoController {

    private final IJuegoService juegoService;

    public JuegoController(IJuegoService juegoService) {
        this.juegoService = juegoService;
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {

        model.addAttribute("juego",new Juego());

        return "juego/formulario";
    }


    @PostMapping("/guardar")
    public String guardarJuego(@ModelAttribute("juego") Juego juego){

        juegoService.saveOrUpdate(juego);

        return "redirect:/juego/crear";

    }
}



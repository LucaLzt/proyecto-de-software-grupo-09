package com.unla.grupo9.steam.controllers;

import com.unla.grupo9.steam.services.IJuegoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {

    private final IJuegoService juegoService;

    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("juegos", juegoService.obtenerTodos());
        return "home";
    }
}
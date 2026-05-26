package com.unla.grupo9.steam.controllers;

import com.unla.grupo9.steam.entities.Juego;
import com.unla.grupo9.steam.services.IJuegoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/juego")
@RequiredArgsConstructor
public class JuegoController {

    private final IJuegoService juegoService;

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("juego", new Juego());
        return "juego/alta-juego";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("juego") Juego juego,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "juego/alta-juego";
        }

        limpiarCamposOpcionales(juego);
        juegoService.guardar(juego);
        redirectAttributes.addFlashAttribute("mensajeExito", "El juego se cargó correctamente");
        return "redirect:/catalogo";
    }

    private void limpiarCamposOpcionales(Juego juego) {
        if (juego.getImagenExtra1() != null && juego.getImagenExtra1().isBlank()) {
            juego.setImagenExtra1(null);
        }
        if (juego.getImagenExtra2() != null && juego.getImagenExtra2().isBlank()) {
            juego.setImagenExtra2(null);
        }
    }
}


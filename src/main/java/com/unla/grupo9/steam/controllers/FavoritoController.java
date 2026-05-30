package com.unla.grupo9.steam.controllers;

import com.unla.grupo9.steam.entities.Juego;
import com.unla.grupo9.steam.entities.User;
import com.unla.grupo9.steam.services.IFavoritoService;
import com.unla.grupo9.steam.services.IJuegoService;
import com.unla.grupo9.steam.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/favoritos")
@RequiredArgsConstructor
public class FavoritoController {

    private final IUserService userService;
    private final IJuegoService juegoService;
    private final IFavoritoService favoritoService;

    @PostMapping("/agregar/{juegoId}")
    public String agregarFavorito(@PathVariable("juegoId") Long juegoId, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username).orElse(null);
        Juego juego = juegoService.obtenerJuegoPorId(juegoId);

        if (user != null && juego != null) {
            if (favoritoService.esFavorito(user, juego)) {
                redirectAttributes.addFlashAttribute("mensaje", "Este juego ya está en tus favoritos.");
            } else {
                favoritoService.agregarFavorito(user, juego);
                redirectAttributes.addFlashAttribute("mensaje", "Juego añadido a favoritos.");
            }
        }
        return "redirect:/catalogo/" + juegoId;
    }

    @PostMapping("/eliminar/{juegoId}")
    public String eliminarFavorito(@PathVariable("juegoId") Long juegoId, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username).orElse(null);
        Juego juego = juegoService.obtenerJuegoPorId(juegoId);

        if (user != null && juego != null) {
            favoritoService.eliminarFavorito(user, juego);
            redirectAttributes.addFlashAttribute("mensaje", "Juego eliminado de favoritos.");
        }
        return "redirect:/catalogo/" + juegoId;
    }
}
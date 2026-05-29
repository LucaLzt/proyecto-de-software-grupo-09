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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/catalogo")
@RequiredArgsConstructor
public class CatalogoController {

    private final IJuegoService juegoService;
    private final IUserService userService;
    private final IFavoritoService favoritoService;

    @GetMapping
    public String mostrarCatalogo(Model model,
                                  @RequestParam(name = "busqueda", required = false) String busqueda,
                                  @RequestParam(name = "genero", required = false) String genero) {
        var juegos = (busqueda != null && !busqueda.isBlank())
                ? juegoService.buscarPorNombre(busqueda)
                : (genero != null && !genero.isBlank())
                  ? juegoService.buscarPorGenero(genero)
                  : juegoService.obtenerTodos();

        model.addAttribute("juegos", juegos);
        model.addAttribute("busqueda", busqueda);
        model.addAttribute("generoSeleccionado", genero);
        return "catalogo";
    }

    @GetMapping("/{id}")
    public String verDetalle(@PathVariable(name="id") Long id, Model model) {
        Juego juego = juegoService.obtenerPorId(id);
        model.addAttribute("juego", juego);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getName())) {
            User user = userService.findByUsername(auth.getName()).orElse(null);
            if (user != null) {
                model.addAttribute("esFavorito", favoritoService.esFavorito(user, juego));
            }
        } else {
            model.addAttribute("esFavorito", false);
        }
        return "detalle";
    }
}
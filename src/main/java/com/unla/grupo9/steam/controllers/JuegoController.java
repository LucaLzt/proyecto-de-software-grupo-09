package com.unla.grupo9.steam.controllers;

import com.unla.grupo9.steam.entities.Juego;
import com.unla.grupo9.steam.entities.User;
import com.unla.grupo9.steam.services.IJuegoService;
import com.unla.grupo9.steam.services.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/juego")
@RequiredArgsConstructor
public class JuegoController {

    private final IJuegoService juegoService;
    private final IUserService userService;

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("juego", new Juego());
        return "juego/alta-juego";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("juego") Juego juego,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes,
                          Principal principal) {
        if (bindingResult.hasErrors()) {
            return "juego/alta-juego";
        }

        if (principal == null) {
            redirectAttributes.addFlashAttribute("mensajeError", "Debes iniciar sesión para crear juegos.");
            return "redirect:/login";
        }

        String usernameLogueado = principal.getName();
        java.util.Optional<User> usuarioOpt = userService.findByUsername(usernameLogueado);
        if (usuarioOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("mensajeError", "Usuario logueado no encontrado en la base de datos.");
            return "redirect:/catalogo";
        }
        juego.setCreador(usuarioOpt.get());

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

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id,
                           Principal principal,
                           RedirectAttributes redirectAttributes) {

        // 1. Buscamos el juego que se quiere eliminar
        Juego juego = juegoService.obtenerPorId(id);

        if (juego != null) {
            // 2. Verificamos que el usuario logueado sea el dueño
            String usernameLogueado = principal.getName();

            if (juego.getCreador() != null && juego.getCreador().getUsername().equals(usernameLogueado)) {
                // 3. Si coincide, lo borramos de la base de datos
                juegoService.eliminar(id); // Asegurate de que tu servicio tenga este método (ej: eliminar o delete)
                redirectAttributes.addFlashAttribute("mensajeExito", "El juego se eliminó correctamente.");
            } else {
                // Si intentó hackear la URL metiendo un ID que no le pertenece
                redirectAttributes.addFlashAttribute("mensajeError", "No tenés permisos para eliminar este juego.");
            }
        } else {
            redirectAttributes.addFlashAttribute("mensajeError", "El juego no existe.");
        }

        // 4. Redirigimos siempre de vuelta al catálogo
        return "redirect:/catalogo";
    }
}


package com.unla.grupo9.steam.controllers;

import com.unla.grupo9.steam.entities.Carrito;
import com.unla.grupo9.steam.entities.Juego;
import com.unla.grupo9.steam.entities.User;
import com.unla.grupo9.steam.services.ICarritoService;
import com.unla.grupo9.steam.services.IJuegoService;
import com.unla.grupo9.steam.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private ICarritoService carritoService;

    @Autowired
    private IJuegoService juegoService;

    @Autowired
    private IUserService userService;

    @GetMapping("")
    public ModelAndView verCarrito() {
        ModelAndView modelAndView = new ModelAndView("carrito");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username).orElse(null);
        if (user != null) {
            Carrito carrito = carritoService.obtenerCarrito(user.getId());
            modelAndView.addObject("carrito", carrito);
        }
        return modelAndView;
    }

    @PostMapping("/agregar/{juegoId}")
    public String agregarAlCarrito(@PathVariable("juegoId") Long juegoId, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username).orElse(null);
        if (user != null) {
            Juego juego = juegoService.obtenerJuegoPorId(juegoId);
            if (juego != null) {
                carritoService.agregarAlCarrito(user.getId(), juego);
                redirectAttributes.addFlashAttribute("mensajeExito", "¡Juego añadido al carrito!");
            }
        }
        return "redirect:/catalogo";
    }

    @PostMapping("/eliminar/{juegoId}")
    public String eliminarDelCarrito(@PathVariable("juegoId") Long juegoId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username).orElse(null);
        if (user != null) {
            carritoService.eliminarDelCarrito(user.getId(), juegoId);
        }
        return "redirect:/carrito";
    }

    @PostMapping("/comprar")
    public String comprarCarrito(RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username).orElse(null);
        if (user != null) {
            carritoService.comprar(user.getId());
            redirectAttributes.addFlashAttribute("mensajeCompra", "¡Juego comprado!");
        }
        return "redirect:/carrito";
    }
}
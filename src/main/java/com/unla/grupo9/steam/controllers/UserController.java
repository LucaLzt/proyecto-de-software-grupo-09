package com.unla.grupo9.steam.controllers;

import com.unla.grupo9.steam.entities.User;
import com.unla.grupo9.steam.services.implementation.UserRoleService;
import com.unla.grupo9.steam.services.implementation.UserService;
import com.unla.grupo9.steam.utils.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class UserController {

    private final UserRoleService roleService;
    private final UserService userService;

    @GetMapping("/registro")
    public String registrar(Model model) {
        model.addAttribute("usuario", new User());
        return "registro";
    }

    @PostMapping("/save")
    public String guardar(@ModelAttribute("usuario") User user, BindingResult bindingResult, Model model) {

        if (user.getUsername() == null || user.getUsername().isBlank()) {
            bindingResult.rejectValue("username", "required", "El email es obligatorio");

        } else if (user.getUsername().length() > 45) {
            bindingResult.rejectValue("username", "length", "El email no puede superar 45 caracteres");
        }

        if (user.getPassword() == null || user.getPassword().isBlank()) {
            bindingResult.rejectValue("password", "required", "La contraseña es obligatoria");

        } else if (user.getPassword().length() < 4 || user.getPassword().length() > 72) {
            bindingResult.rejectValue("password", "length",
                    "La contraseña debe tener entre 4 y 72 caracteres");
        }

        if (bindingResult.hasErrors()) {
            return "registro";
        }

        var auditorRole = roleService.traerPorNombre(Roles.ROLE_AUDITOR);

        if (auditorRole.isEmpty()) {
            model.addAttribute("registroError",
                    "No se pudo asignar el rol de usuario");

            return "registro";
        }

        user.setRoles(List.of(auditorRole.get()));

        try {
            userService.saveOrUpdate(user);

        } catch (DataIntegrityViolationException ex) {

            bindingResult.rejectValue("username",
                    "duplicate",
                    "Ese email ya está registrado");

            return "registro";
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model,
                        @RequestParam(name = "error", required = false) String error,
                        @RequestParam(name = "logout", required = false) String logout) {
        model.addAttribute("error", error);
        model.addAttribute("logout", logout);
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

    @GetMapping("/loginsuccess")
    public String loginCheck() {
        return "redirect:/home";
    }
}
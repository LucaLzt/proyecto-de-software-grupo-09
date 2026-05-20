package com.unla.grupo9.steam.controllers;

import com.unla.grupo9.steam.entities.User;
import com.unla.grupo9.steam.services.implementation.UserRoleService;
import com.unla.grupo9.steam.services.implementation.UserService;
import com.unla.grupo9.steam.utils.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;
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
    public RedirectView guardar(@ModelAttribute("usuario") User user) {
        user.setRoles(List.of(roleService.traerPorNombre(Roles.ROLE_AUDITOR).get()));
        userService.saveOrUpdate(user);
        return new RedirectView("/login");
    }

    @GetMapping("/login")
    public String login(Model model, @RequestParam(name = "error", required = false) String error,
                        @RequestParam(name = "logout", required = false) String logout) {
        model.addAttribute("error", error);
        model.addAttribute("logout", logout);
        return "login";
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        return "redirect:/login";
    }

    @GetMapping("/loginsuccess")
    public String loginCheck() {
        return "redirect:/home";
    }
}
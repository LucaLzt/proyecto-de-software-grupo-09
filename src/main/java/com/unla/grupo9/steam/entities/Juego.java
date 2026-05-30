package com.unla.grupo9.steam.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "juegos")
public class Juego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // El creador se asigna en el servidor al guardar el formulario
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario_creador", nullable = true)
    private User creador;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @PositiveOrZero(message = "El precio no puede ser negativo")
    private Double precio;

    @NotBlank(message = "La imagen principal es obligatoria")
    private String imagen;

    @NotBlank(message = "El género es obligatorio")
    private String genero;

    @NotBlank(message = "Los requisitos mínimos son obligatorios")
    private String requisitosMinimos;

    @NotBlank(message = "Los requisitos recomendados son obligatorios")
    private String requisitosRecomendados;

    @NotBlank(message = "El desarrollador es obligatorio")
    private String desarrollador;

    private String imagenExtra1;

    private String imagenExtra2;

    @ManyToMany(mappedBy = "favoritos")
    private Set<User> fans;
}
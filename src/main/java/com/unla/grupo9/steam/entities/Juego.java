package com.unla.grupo9.steam.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "juegos")
public class Juego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private Double precio;
    private String imagen;
    private String genero;
    
   
}
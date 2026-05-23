package com.unla.grupo9.steam.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Juego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", unique = true, nullable = false, length = 45)
    private String nameGame;


    @Column(name = "descripcion", nullable = false, length = 200)
    private String description;

    @Column(name = "precio", nullable = false)
    private float price;


    public Juego(String nameGame, String description, float price) {
        super();
        this.nameGame = nameGame;
        this.description = description;
        this.price = price;
    }
}

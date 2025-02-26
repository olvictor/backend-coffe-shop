package com.example.coffe_shop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Entity
@Table(name="usuarios")
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;

    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String nome;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Usuario(String password, String email, String nome) {
        this.password = password;
        this.email = email;
        this.nome = nome;
    }

    public  Usuario (){

    }


}

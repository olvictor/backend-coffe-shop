package com.example.coffe_shop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name="usuarios")
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Column(nullable = false)
    private String nome;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Usuario(String email, String password, String nome) {
        this.email = email;
        this.password = password;
        this.nome = nome;
    }

    public  Usuario (){

    }


}

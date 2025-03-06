package com.example.coffe_shop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;


@Entity
@Table(name ="produtos")
@Getter
@Setter
public class Produto {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String nome;

    private double preco;

    private int quantidade;

    private String image_url;

    public Produto(String nome, double preco, int quantidade, String image_url) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.image_url = image_url;
    }

    public Produto(){

    }
}




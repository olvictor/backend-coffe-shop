package com.example.coffe_shop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    @GetMapping
    public ResponseEntity<?> buscarProdutos(){
        return ResponseEntity.ok().body("aaaaaaaaaaaaaaa");
    }
}

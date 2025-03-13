package com.example.coffe_shop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {

    @PostMapping()
    public ResponseEntity<?> cadastrarPedido(){
        return ResponseEntity.ok().body("testando rota.");
    }
}

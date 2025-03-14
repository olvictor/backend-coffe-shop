package com.example.coffe_shop.controller;

import com.example.coffe_shop.DTO.PedidosRequestDTO;
import com.example.coffe_shop.model.Pedido;
import com.example.coffe_shop.model.Produto;
import com.example.coffe_shop.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping()
    public ResponseEntity<?> cadastrarPedido(@RequestBody PedidosRequestDTO data){
       Optional<Pedido> cadastrarPedido = (Optional<Pedido>) pedidoService.cadastrarPedido(data);
       return ResponseEntity.ok().body(cadastrarPedido);
    }
}

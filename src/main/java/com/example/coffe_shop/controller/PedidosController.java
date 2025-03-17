package com.example.coffe_shop.controller;

import com.example.coffe_shop.DTO.PedidosRequestDTO;
import com.example.coffe_shop.model.Pedido;
import com.example.coffe_shop.model.Produto;
import com.example.coffe_shop.service.PedidoService;
import com.example.coffe_shop.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {

    @Autowired
    private PedidoService pedidoService;


    @PostMapping()
    public ResponseEntity<?> cadastrarPedido(@RequestBody PedidosRequestDTO data, @RequestHeader("Authorization") String header){
       Optional<Pedido> cadastrarPedido = (Optional<Pedido>) pedidoService.cadastrarPedido(data,header);
       return ResponseEntity.ok().body(cadastrarPedido);
    }

    @GetMapping()
    public ResponseEntity<?>buscarPedidos(@RequestHeader("Authorization") String header, @RequestParam(required = false) Optional<UUID> id){
        if(id.isPresent()){
            Optional<?> pedido = pedidoService.buscarPedido(id.get());
            return ResponseEntity.ok().body(pedido);
        }
        Optional<?> pedidos = pedidoService.buscarPedidos(header);
        return ResponseEntity.ok().body(pedidos);
    }
}

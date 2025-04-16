package com.example.coffe_shop.controller;

import com.example.coffe_shop.DTO.PedidosRequestDTO;
import com.example.coffe_shop.DTO.PedidosResponseDTO;
import com.example.coffe_shop.model.Pedido;
import com.example.coffe_shop.model.Produto;
import com.example.coffe_shop.service.PedidoService;
import com.example.coffe_shop.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        try{
            Optional<Pedido> cadastrarPedido = (Optional<Pedido>) pedidoService.cadastrarPedido(data,header);

            if (cadastrarPedido.isPresent()) {
                Pedido pedido = cadastrarPedido.get();
                PedidosResponseDTO responseDTO = PedidosResponseDTO.from(pedido);
                return ResponseEntity.ok().body(responseDTO);
            }else {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Não foi possível cadastrar o pedido.");
            }
        } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Erro ao processar o pedido: " + e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<?>buscarPedidos(@RequestHeader("Authorization") String header, @RequestParam(required = false) Optional<UUID> id){
        try{
            if(id.isPresent()){
                Optional<Pedido> pedido = pedidoService.buscarPedido(id.get());
                if (pedido.isPresent()) {
                    PedidosResponseDTO responseDTO = PedidosResponseDTO.from(pedido.get());
                    return ResponseEntity.ok().body(responseDTO);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado");
                }
            }
            Optional<List<Pedido>> pedidos = (Optional<List<Pedido>>) pedidoService.buscarPedidos(header);

            return ResponseEntity.ok().body(pedidos);
        } catch (Exception e) {
            return ResponseEntity.ok().body(e.getMessage());
        }
    }
}

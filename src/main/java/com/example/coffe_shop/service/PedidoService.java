package com.example.coffe_shop.service;

import com.example.coffe_shop.DTO.PedidosRequestDTO;
import com.example.coffe_shop.model.Pedido;
import com.example.coffe_shop.model.Produto;
import com.example.coffe_shop.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    ProdutoService produtoService;

    public Optional<?> cadastrarPedido(PedidosRequestDTO data){

        Double valorTotal = 0.0;

        for (Produto produto : data.produtos()) {
            valorTotal += produto.getPreco();
        }
        Boolean validado = produtoService.produtoEmEstoque(data.produtos());
        if(validado){
            Pedido novoPedido = new Pedido(data.produtos(), valorTotal);
            return Optional.of(pedidoRepository.save(novoPedido));
        }

        return null;
    }
}

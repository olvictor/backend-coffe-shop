package com.example.coffe_shop.service;

import com.example.coffe_shop.DTO.PedidosRequestDTO;
import com.example.coffe_shop.model.Pedido;
import com.example.coffe_shop.model.Produto;
import com.example.coffe_shop.model.Usuario;
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

    @Autowired
    UsuarioService usuarioService;

    public Optional<?> cadastrarPedido(PedidosRequestDTO data, String header){
        String token = header.replace("Bearer ","");
        Optional<Usuario> usuarioLogado = usuarioService.buscarUsuarioByToken(token);
        Double valorTotal = 0.0;

        for (Produto produto : data.produtos()) {
            valorTotal += produto.getPreco();
        }
        Boolean validado = produtoService.produtoEmEstoque(data.produtos());
        System.out.println(validado);
        if(validado){
            Pedido novoPedido = new Pedido(data.produtos(), valorTotal, usuarioLogado.get());
            return Optional.of(pedidoRepository.save(novoPedido));
        }

        return null;
    }
}

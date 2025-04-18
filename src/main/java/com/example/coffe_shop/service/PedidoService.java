package com.example.coffe_shop.service;

import com.example.coffe_shop.DTO.PedidosRequestDTO;
import com.example.coffe_shop.model.Pedido;
import com.example.coffe_shop.model.Produto;
import com.example.coffe_shop.model.Usuario;
import com.example.coffe_shop.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

        List<Produto> produtos = data.produtos().stream()
                .map(p -> produtoService.listarProdutoById(p.getId()))
                .filter(opt -> opt.isPresent())
                .map(opt -> opt.get())
                .collect(Collectors.toList());

        Double valorTotal = 0.0;

        for (Produto produto : produtos) {
            valorTotal += produto.getPreco();
        }

        Boolean validado = produtoService.produtoEmEstoque(data.produtos());

        if(validado){
            Pedido novoPedido = new Pedido(produtos, valorTotal, usuarioLogado.get());
            return Optional.of(pedidoRepository.save(novoPedido));
        }

        return null;
    }

    public Optional<?> buscarPedidos(String header){
        String token = header.replace("Bearer ","");
        Optional<Usuario> usuario = usuarioService.buscarUsuarioByToken(token);
        return Optional.ofNullable(pedidoRepository.findByUsuarioId(usuario.get().getId()));
    }

    public Optional<Pedido> buscarPedido(UUID id) {
        return pedidoRepository.findById(id);
    }
}

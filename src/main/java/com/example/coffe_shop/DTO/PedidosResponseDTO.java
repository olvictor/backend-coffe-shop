package com.example.coffe_shop.DTO;

import com.example.coffe_shop.model.Pedido;
import com.example.coffe_shop.model.StatusDoPedido;
import com.example.coffe_shop.model.Usuario;

import java.time.LocalDateTime;
import java.util.List;

import java.util.UUID;

public record PedidosResponseDTO(
        UUID id,
        List<ProdutosResponseDTO> produtos,
        String status_do_pedido,
        Double valor_total,
        LocalDateTime createdAt,
        UsuarioResponseDTO usuario
) {
    public static PedidosResponseDTO from(Pedido pedido) {
        List<ProdutosResponseDTO> produtosDTO = pedido.getProdutos().stream()
                .map(produto -> new ProdutosResponseDTO(
                        produto.getNome(),
                        produto.getPreco(),
                        produto.getQuantidade(),
                        produto.getImage_url()
                ))
                .toList();

        Usuario usuario = pedido.getUsuario();
        UsuarioResponseDTO usuarioDTO = new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getNome(),
                usuario.getCreatedAt(),
                usuario.getUpdatedAt()
        );

        return new PedidosResponseDTO(
                pedido.getId(),
                produtosDTO,
                pedido.getStatus_do_pedido().toString(),
                pedido.getValor_total(),
                pedido.getCreatedAt(),
                usuarioDTO
        );
    }
}
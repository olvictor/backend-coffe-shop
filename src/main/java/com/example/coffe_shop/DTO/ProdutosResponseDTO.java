package com.example.coffe_shop.DTO;

import com.example.coffe_shop.model.Produto;


import java.util.Optional;

public record ProdutosResponseDTO(String nome, Double preco, int quantidade, String url) {

    public ProdutosResponseDTO(Optional<Produto> optionalProduto){
        this(
                optionalProduto.map(Produto::getNome).orElse(null),
                optionalProduto.map(Produto::getPreco).orElse(null),
                optionalProduto.map(Produto::getQuantidade).orElse(null),
                optionalProduto.map(Produto::getImage_url).orElse(null)
        );
    }


}

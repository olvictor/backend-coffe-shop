package com.example.coffe_shop.service;

import com.example.coffe_shop.model.Produto;
import com.example.coffe_shop.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public Produto cadastrarProduto(String nome, double preco, int qunatidade, String image_url){
        Produto novoProduto = new Produto(nome, preco,qunatidade,image_url);
        return produtoRepository.save(novoProduto);
    }
}

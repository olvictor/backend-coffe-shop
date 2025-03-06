package com.example.coffe_shop.controller;

import com.example.coffe_shop.model.Produto;
import com.example.coffe_shop.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<?> buscarProdutos(){
        List<Produto> produtos = produtoService.listarProduto();
        return ResponseEntity.ok().body(produtos);
    }

    @PostMapping()
    public  ResponseEntity<?>cadastrarProduto(@RequestBody Produto produto){
        Produto novoProduto = produtoService.cadastrarProduto(produto.getNome(),produto.getPreco(),produto.getQuantidade(), produto.getImage_url());
        return ResponseEntity.ok().body(novoProduto);
    }
}

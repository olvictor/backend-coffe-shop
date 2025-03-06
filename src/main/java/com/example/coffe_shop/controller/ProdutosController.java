package com.example.coffe_shop.controller;

import com.example.coffe_shop.model.Produto;
import com.example.coffe_shop.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<?> buscarProdutos(){
        return ResponseEntity.ok().body("aaaaaaaaaaaaaaa");
    }

    @PostMapping()
    public  ResponseEntity<?>cadastrarProduto(@RequestBody Produto produto){

      Produto novoProduto = produtoService.cadastrarProduto(produto.getNome(),produto.getPreco(),produto.getQuantidade(), produto.getImage_url());

      return ResponseEntity.ok().body(novoProduto);
    }
}

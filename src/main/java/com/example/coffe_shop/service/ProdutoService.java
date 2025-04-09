package com.example.coffe_shop.service;

import com.example.coffe_shop.model.Produto;
import com.example.coffe_shop.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public List<Produto> listarProduto (){
       return produtoRepository.findAll();
    }

    public Optional<Produto> listarProdutoById(UUID uuid) { return produtoRepository.findById(uuid);}

    public Boolean produtoEmEstoque(List<Produto> data){
        Map<String, Integer> produtos = new HashMap<>();

        for (Produto produto : data) {
            String chave = String.valueOf(produto.getId());
            if(!produtos.containsKey(chave)){
                produtos.put(chave,1);
            }else{
                produtos.put(chave, produtos.get(chave) + 1);
            }
        }

        for (Map.Entry<String, Integer> entry : produtos.entrySet()) {
            String chave = entry.getKey();
            Integer valor = entry.getValue();

            System.out.println("Produto: " + chave + ", Quantidade: " + valor);
            Optional<Produto> produto = produtoRepository.findById(UUID.fromString(chave));
            if(valor > produto.get().getQuantidade() ){
                return false;
            }
        }
        return true;
    }
}

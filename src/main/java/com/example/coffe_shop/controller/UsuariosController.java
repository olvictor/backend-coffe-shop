package com.example.coffe_shop.controller;

import com.example.coffe_shop.model.Usuario;
import com.example.coffe_shop.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UsuariosController {

    UsuarioRepository usuarioRepository;

    @PostMapping("/register")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody Usuario usuario){
        Usuario novoUsuario = new Usuario(usuario.getEmail(),usuario.getPassword(), usuario.getNome());
        usuarioRepository.save(novoUsuario);

        return ResponseEntity.ok(novoUsuario);
    }
}

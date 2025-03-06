package com.example.coffe_shop.controller;


import com.example.coffe_shop.model.Usuario;
import com.example.coffe_shop.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("usuario")
public class UsuariosController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping()
    public ResponseEntity<?> trocarSenha(@RequestHeader("Authorization") String header){

        Optional<Usuario> usuario = usuarioService.buscarUsuarioByToken(header.replace("Bearer ",""));
        System.out.println(usuario.get().getId());
        return ResponseEntity.ok().body("aaaaaaaasa");
    }
}

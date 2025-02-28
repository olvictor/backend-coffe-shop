package com.example.coffe_shop.controller;

import com.example.coffe_shop.model.Usuario;
import com.example.coffe_shop.repository.UsuarioRepository;
import com.example.coffe_shop.security.JwtUtil;
import com.example.coffe_shop.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class UsuariosController {


    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody Usuario usuario){
        Usuario user = usuarioService.RegistrarUsuario(usuario.getEmail(), usuario.getPassword(), usuario.getNome());

        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> logarUsuario(@RequestBody Usuario usuario){
        Optional<Usuario> usuarioValidado = usuarioService.ValidarUsuario(usuario);

        System.out.println(usuarioValidado);

        if(usuarioValidado.isPresent()){
            String token = JwtUtil.gerarToken(usuarioValidado.get().getEmail());

            return ResponseEntity.ok().body(token);
        }

        return ResponseEntity.status(401).body("Credenciais inválidas.");

    }
}

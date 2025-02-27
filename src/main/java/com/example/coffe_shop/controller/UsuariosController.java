package com.example.coffe_shop.controller;

import com.example.coffe_shop.model.Usuario;
import com.example.coffe_shop.repository.UsuarioRepository;
import com.example.coffe_shop.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @PostMapping("/login")
    public ResponseEntity<?> logarUsuario(@RequestBody Usuario usuario){
        Optional<Usuario> user = usuarioRepository.findByEmail(usuario.getEmail());

        if(user.isPresent() && user.get().getPassword().equals(usuario.getPassword())){
            String token = JwtUtil.gerarToken(user.get().getEmail());

            return ResponseEntity.ok().body(token);
        }

        return ResponseEntity.status(401).body("Credenciais inválidas.");

    }
}

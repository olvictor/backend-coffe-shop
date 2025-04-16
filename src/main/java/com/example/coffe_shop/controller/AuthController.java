package com.example.coffe_shop.controller;

import com.example.coffe_shop.DTO.UsuarioResponseDTO;
import com.example.coffe_shop.model.Usuario;
import com.example.coffe_shop.security.JwtUtil;
import com.example.coffe_shop.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody Usuario usuario){
        try{
            Usuario user = usuarioService.RegistrarUsuario(usuario.getEmail(), usuario.getPassword(), usuario.getNome());
            return ResponseEntity.ok().body(new UsuarioResponseDTO(Optional.ofNullable(user)));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> logarUsuario(@RequestBody Usuario usuario){
        try{
            Optional<Usuario> usuarioValidado = usuarioService.ValidarUsuario(usuario);

            if(usuarioValidado != null){
                String token = JwtUtil.gerarToken(usuarioValidado.get().getEmail());

                return ResponseEntity.ok().body(token);
            }

            return ResponseEntity.status(401).body("Credenciais inválidas.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

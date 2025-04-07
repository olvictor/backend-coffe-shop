package com.example.coffe_shop.DTO;


import com.example.coffe_shop.model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Optional;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UsuarioResponseDTO(UUID id, String nome, String email) {
    public UsuarioResponseDTO(Optional<Usuario> optionalUsuario) {
        this(
                optionalUsuario.map(Usuario::getId).orElse(null),
                optionalUsuario.map(Usuario::getNome).orElse(null),
                optionalUsuario.map(Usuario::getEmail).orElse(null)
        );
    }
}

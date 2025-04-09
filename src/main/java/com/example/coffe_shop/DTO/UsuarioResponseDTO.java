package com.example.coffe_shop.DTO;


import com.example.coffe_shop.model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UsuarioResponseDTO(
        UUID id,
        String email,
        String nome,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public UsuarioResponseDTO(Optional<Usuario> optionalUsuario) {
        this(
                optionalUsuario.map(Usuario::getId).orElse(null),
                optionalUsuario.map(Usuario::getEmail).orElse(null),
                optionalUsuario.map(Usuario::getNome).orElse(null),
                optionalUsuario.map(Usuario::getCreatedAt).orElse(null),
                optionalUsuario.map(Usuario::getUpdatedAt).orElse(null)
        );
    }
}

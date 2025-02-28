package com.example.coffe_shop.service;

import com.example.coffe_shop.model.Usuario;
import com.example.coffe_shop.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public UsuarioService (UsuarioRepository usuarioRepository){
         this.usuarioRepository = usuarioRepository;
    }



    public Usuario RegistrarUsuario (String email, String password, String nome){

        Optional<Usuario> usuarioCadastrado = usuarioRepository.findByEmail(email);

        if(usuarioCadastrado.isPresent()){
            throw new IllegalArgumentException("Usuário com este e-mail já está cadastrado.");
        }
        System.out.println(usuarioCadastrado);
        String senhaCriptografada = passwordEncoder.encode(password);

        Usuario usuario = new Usuario(email, senhaCriptografada, nome);

        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> ValidarUsuario (Usuario usuario){
        Optional<Usuario> user = usuarioRepository.findByEmail(usuario.getEmail());

        boolean password = passwordEncoder.matches(usuario.getPassword(),user.get().getPassword());

        return user;

    }
}

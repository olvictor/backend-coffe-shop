package com.example.coffe_shop.service;

import com.example.coffe_shop.model.Usuario;
import com.example.coffe_shop.repository.UsuarioRepository;
import com.example.coffe_shop.security.JwtUtil;
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

    @Autowired
    private final JwtUtil jwtUtil;

    public UsuarioService (UsuarioRepository usuarioRepository, JwtUtil jwtUtil){
         this.usuarioRepository = usuarioRepository;
         this.jwtUtil = jwtUtil;
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

        Boolean password = passwordEncoder.matches(usuario.getPassword(),user.get().getPassword());

        if(password){
            return user;
        }
        return null;
    }

    public  Optional<Usuario> buscarUsuarioByToken(String token){
        String subject = jwtUtil.getSubject(token);

        Optional<?> usuario = usuarioRepository.findByEmail(subject);

        return (Optional<Usuario>) usuario;
    }

}

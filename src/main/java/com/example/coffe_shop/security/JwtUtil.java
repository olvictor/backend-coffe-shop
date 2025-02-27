package com.example.coffe_shop.security;


import com.example.coffe_shop.model.Usuario;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class JwtUtil {
    @Value("${api.security.token.secret}")
    private Key secretKey;

    private static long EXPIRATION_TIME = 86400000;

    public String gerarToken(Usuario usuario){
        String jwt = Jwts.builder()
                .subject(String.valueOf(usuario))
                .signWith(secretKey)
                .expiration(dataExpiracao())
                .compact();

        return jwt;
    }

    private Date dataExpiracao() {
        return Date.from(Instant.now().plus(24, ChronoUnit.HOURS));
    }
}

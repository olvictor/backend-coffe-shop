package com.example.coffe_shop.security;


import com.example.coffe_shop.model.Usuario;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class JwtUtil {
    @Value("${api.security.token.secret}")
    private Key secretKey;

    private static long EXPIRATION_TIME = 86400000;

    public String gerarToken(Usuario usuario){
        String jwt = Jwts.builder()
                .subject(String.valueOf(usuario.getEmail()))
                .signWith(secretKey)
                .expiration(dataExpiracao())
                .compact();

        return jwt;
    }

    private Date dataExpiracao() {
        return Date.from(Instant.now().plus(24, ChronoUnit.HOURS));
    }


    public String getSubject(String token){
          return Jwts.parser().verifyWith((SecretKey) secretKey).build()
                    .parseSignedClaims(token).getBody().getSubject();
    }

    public boolean validarToken(String token){
        try{
            Jwts.parser().verifyWith((SecretKey) secretKey).build().parseSignedClaims(token);

            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}

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
    private static Key secretKey;

    private static long EXPIRATION_TIME = 86400000;

    public static String gerarToken(String email){
        String jwt = Jwts.builder()
                .subject(email)
                .signWith(secretKey)
                .expiration(dataExpiracao())
                .compact();

        return jwt;
    }

    private static Date dataExpiracao() {
        return Date.from(Instant.now().plus(24, ChronoUnit.HOURS));
    }


    public static String getSubject(String token){
          return Jwts.parser().verifyWith((SecretKey) secretKey).build()
                    .parseSignedClaims(token).getBody().getSubject();
    }

    public static boolean validarToken(String token){
        try{
            Jwts.parser().verifyWith((SecretKey) secretKey).build().parseSignedClaims(token);

            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}

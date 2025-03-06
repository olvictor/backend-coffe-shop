package com.example.coffe_shop.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtUtil {

    private static final Key key= Keys.secretKeyFor(SignatureAlgorithm.HS256);


    public static String gerarToken(String email){
        String jwt = Jwts.builder()
                .subject(email)
                .signWith(key)
                .expiration(dataExpiracao())
                .compact();

        return jwt;
    }

    private static Date dataExpiracao() {
        return Date.from(Instant.now().plus(24, ChronoUnit.HOURS));
    }


    public static String getSubject(String token){
          return Jwts.parser().verifyWith((SecretKey) key).build()
                    .parseSignedClaims(token).getBody().getSubject();
    }

    public static boolean validarToken(String token){
        try{
            Jwts.parser().verifyWith((SecretKey) key).build().parseSignedClaims(token);

            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}

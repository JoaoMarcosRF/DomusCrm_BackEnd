package com.domus.api.service.jwt;

import com.domus.api.model.broker.Broker;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    SecretKey key = Keys.hmacShaKeyFor("UNA_BERRETA_CUANDO".getBytes());

    public String generateToken(Broker broker) {
        return Jwts.builder()
                .setSubject(broker.getEmail())
                .claim("id",broker.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1300000))
                .signWith(key)
                .compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    public boolean isTokenValid(String token) {
        try{
            extractAllClaims(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}

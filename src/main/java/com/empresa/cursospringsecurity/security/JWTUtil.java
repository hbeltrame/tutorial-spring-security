package com.empresa.cursospringsecurity.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Utilitário para geração de tokens
 *
 */
@Component
public class JWTUtil {

	@Value("${jwt.secret}")  // valor especificado no application.properties
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	public String generateToken(String username) {
		return Jwts.builder()
				   .setSubject(username)
				   .setExpiration(new Date(System.currentTimeMillis() + expiration))
				   .signWith(SignatureAlgorithm.HS512, secret.getBytes())  // algorítmo e chave de assinatura
				   .compact();
	}

	public boolean isValidToken(String token) {
		Claims claims = getClaims(token);  // armazena as reivindicações contidas no token
		if (claims != null) {
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis()); // para testar se o token expirou
			if (username != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}
		}
		return false;
	}
	
	public String getUsername(String token) {
		Claims claims = getClaims(token);  // armazena as reivindicações contidas no token
		if (claims != null) {
			return claims.getSubject();
		}
		return null;
	}

	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			return null;
		}
	}
}

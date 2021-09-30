package com.yelp.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtUtil {
	
	private final String SECRET = "WEIRD";

	public String makeToken(UserDetails user) {
		HashMap<String, Object> claims = new HashMap();
		return createToken(claims, user.getUsername());
	}
	
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	public boolean isExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims getAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
	}
	
	public boolean validate(String token, UserDetails user) {
		return (extractUsername(token).equals(user.getUsername()) && !isExpired(token));
	}
	
	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60)))
				.signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, SECRET)
				.compact();
	}
}

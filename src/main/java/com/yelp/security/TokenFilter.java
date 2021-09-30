package com.yelp.security;

import org.springframework.web.filter.OncePerRequestFilter;

import com.yelp.service.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.FilterChain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import java.io.IOException;

@Service
public class TokenFilter extends OncePerRequestFilter{
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
		throws ServletException, IOException{
		String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		UserDetails user;
		if(authHeader != null && authHeader.startsWith("Bearer ") 
				&& SecurityContextHolder.getContext().getAuthentication() == null) {
			String token = authHeader.substring(7);
			String username = jwtUtil.extractUsername(token);
			user = userDetailsService.loadUserByUsername(username);
			if(user != null && jwtUtil.validate(token, user)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthToken = new UsernamePasswordAuthenticationToken(
						user, null, user.getAuthorities());
				usernamePasswordAuthToken.setDetails(
						new WebAuthenticationDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthToken);
			}
		}
		chain.doFilter(request, response);
	}
	
}


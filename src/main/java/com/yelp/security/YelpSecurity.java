package com.yelp.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class YelpSecurity extends WebSecurityConfigurerAdapter{

	
	@Autowired
	UserDetailsService detailsService;
	
	@Autowired
	TokenFilter tokenFilter;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(detailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	public void configure(HttpSecurity httpSec) throws Exception {
		
		
		httpSec
			.cors()
		.and()
			.csrf()
				.disable()
			.authorizeRequests()
				.antMatchers("/login", "/register")
					.permitAll()
				.anyRequest()
					.authenticated()
		.and()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
			.addFilterBefore(tokenFilter,	UsernamePasswordAuthenticationFilter.class);
	}
}

package com.jwt.jwtToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class MySecurityConfig {
	
	@Autowired
	private JwtAuthenticationFilter jwtFilter;
	
	
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	     
	      http.csrf()
	      .disable()
	      .cors()
	      .disable()
	      .authorizeRequests()
	      .antMatchers("/token").permitAll()
	      .anyRequest().authenticated()
	      .and()
	      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	       
	      http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	      return http.build();
	    }

    
    @Bean 
    AuthenticationManager authenticationManager()
    {
       return new AuthenticationManager() {
		
		@Override
		public Authentication authenticate(Authentication authentication) throws AuthenticationException {
			// TODO Auto-generated method stub
			return null;
		}
	};	
    }
}
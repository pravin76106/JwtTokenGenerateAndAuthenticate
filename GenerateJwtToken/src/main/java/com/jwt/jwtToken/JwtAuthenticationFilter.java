package com.jwt.jwtToken;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;



@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	// get jwt
	// Bearee
	// validate

	@Autowired
	UserDetailService userDetailService;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String requestTokenHeader = request.getHeader("Authorization");
		String userName = null;
		String jwtToken = null;

		// null and formate
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
             
		
			try {

				userName = this.jwtUtil.getUsernameFromToken(jwtToken);
				System.out.println(userName);
			} catch (Exception e) {

				e.printStackTrace();
			}

			UserDetails userDetails = this.userDetailService.loadUserByUsername(userName);
			// security

			if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				
		
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
			
			   usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

			   SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			
			} else {
				System.err.println("Token is Not Valid");
			}
			
			
		}

		filterChain.doFilter(request, response);
	}

}

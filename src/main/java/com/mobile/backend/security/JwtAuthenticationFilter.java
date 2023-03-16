package com.mobile.backend.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mobile.backend.service.ICustomUserService;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	
	@Autowired
	JwtTokenProvider tokenProvider;
	
	@Autowired
	ICustomUserService userService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException, java.io.IOException {
		
		try {
			String token = tokenProvider.resolveToken(request);
			if(tokenProvider.validateToken(token) && StringUtils.hasText(token)) {
				
				Long userId = tokenProvider.getUserIdFromJwt(token);
				UserPrincipal userPrincipal = userService.loadUserByUserId(userId);
				UsernamePasswordAuthenticationToken authenticationToken = 
						new UsernamePasswordAuthenticationToken(
								userPrincipal, 
								null,
								userPrincipal.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}catch(Exception e) {
			log.error("Could not validate this jwt");
		}
		
		filterChain.doFilter(request, response);
	}

}

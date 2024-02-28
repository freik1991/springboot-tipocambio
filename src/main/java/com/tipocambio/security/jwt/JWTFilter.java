package com.tipocambio.security.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tipocambio.handler.ForbiddenHandler;
import com.tipocambio.handler.UnauthorizedHandler;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilter extends OncePerRequestFilter {

	@Autowired
	private JWTUtil jwtUtil;
	
	private final String authHeader = "Authorization";

	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException,MalformedJwtException {
		// CORS
				response.addHeader("Access-Control-Allow-Origin", "*");
				if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equalsIgnoreCase(request.getMethod())) {
					response.addHeader("Access-Control-Allow-Headers", "Authorization");
					response.addHeader("Access-Control-Allow-Headers", "Content-Type");
					response.addHeader("Access-Control-Max-Age", "1");
					response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
				}

				try {
					String authHeader = request.getHeader(this.authHeader);
					System.out.println("authHeader11:"+authHeader);

					if (authHeader != null && authHeader.startsWith("Bearer ")) {
						String token = authHeader.substring(7);
						System.out.println("token:"+token);
						System.out.println("authHeader22:"+authHeader);
						try {
							if (jwtUtil.validateToken(token)) {

								Claims claims = jwtUtil.getAllClaimsFromToken(token);
								List<String> rolesMap = claims.get("role", List.class);
								List<GrantedAuthority> authorities = new ArrayList<>();
								for (String rolemap : rolesMap) {
									authorities.add(new SimpleGrantedAuthority(rolemap));
								}

								UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities);
								authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
								SecurityContextHolder.getContext().setAuthentication(authentication);
							}
						}catch(MalformedJwtException ex) {
							ex.printStackTrace();
			                chain.doFilter(request, response);
						}
						
					}
	                chain.doFilter(request, response);

				} catch (Exception e) {
					e.printStackTrace();
				}
//				if (!request.getMethod().equalsIgnoreCase("OPTIONS")) {
//					chain.doFilter(request, response);
//				}
	}
}

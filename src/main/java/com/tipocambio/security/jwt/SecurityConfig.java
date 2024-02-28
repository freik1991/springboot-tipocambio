package com.tipocambio.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tipocambio.handler.ForbiddenHandler;
import com.tipocambio.handler.UnauthorizedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private JWTFilter authFilter;

	@Bean
	public UnauthorizedHandler unauthorizedHandler() throws Exception {
		return new UnauthorizedHandler();
	}

	@Bean
	public ForbiddenHandler forbiddenHandler() throws Exception {
		return new ForbiddenHandler();
	}

	// Configuring HttpSecurity
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable().authorizeHttpRequests().requestMatchers("/login").permitAll().anyRequest()
				.authenticated().and()
				// .and().exceptionHandling()
				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler())
				.accessDeniedHandler(forbiddenHandler())
				.and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	// Password Encoding
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}

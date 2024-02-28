package com.tipocambio.services.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tipocambio.bean.Usuario;
import com.tipocambio.dto.AuthRequest;
import com.tipocambio.dto.AuthResponse;
import com.tipocambio.repository.UsuarioRepository;
import com.tipocambio.security.enums.Role;
import com.tipocambio.security.jwt.JWTUtil;
import com.tipocambio.security.jwt.PBKDF2Encoder;
import com.tipocambio.services.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PBKDF2Encoder passwordEncoder;

	@Override
	public ResponseEntity<AuthResponse> login(AuthRequest authRequest) {
	    Usuario u = usuarioRepository.findByUsuario("admin");
		if (u != null) {
			System.out.println("clave:" + passwordEncoder.encode(authRequest.getPassword()));
			if (passwordEncoder.encode(authRequest.getPassword()).equals(u.getPassword())) {
				return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(u)));
			} else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@Override
	public List<Usuario> listarUsuario() {
		return usuarioRepository.findAll();
	}
}

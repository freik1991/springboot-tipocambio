package com.tipocambio.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.tipocambio.bean.Usuario;
import com.tipocambio.dto.AuthRequest;
import com.tipocambio.dto.AuthResponse;

public interface UserService {

	ResponseEntity<AuthResponse> login(AuthRequest authRequest);
	List<Usuario> listarUsuario();
}

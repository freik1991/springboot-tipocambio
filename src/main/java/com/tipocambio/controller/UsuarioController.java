package com.tipocambio.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.tipocambio.bean.Usuario;
import com.tipocambio.dto.AuthRequest;
import com.tipocambio.dto.AuthResponse;
import com.tipocambio.dto.Message;
import com.tipocambio.repository.UsuarioRepository;
import com.tipocambio.request.UsuarioRequest;
import com.tipocambio.services.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UsuarioController {


	@Autowired
	private UserService userService;
	
	@GetMapping("/welcome") 
    public String welcome() { 
	        return "Bienvenido"; 
    } 

  
	@GetMapping("/listarUsuario")
	public List<Usuario> listarUsuarios() {
		return userService.listarUsuario();
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
		try {
			return userService.login(authRequest);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}


}

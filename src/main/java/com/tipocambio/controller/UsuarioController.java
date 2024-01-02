package com.tipocambio.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.tipocambio.repository.UsuarioRepository;
import com.tipocambio.request.UsuarioRequest;
import com.tipocambio.security.jwt.JWTUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	private final PasswordEncoder passwordEncoder = null;
	
	@Autowired
	private JWTUtil jwtUtil;

	@GetMapping("/usuario")
	public Flux<Usuario> listarUsuarios() {
		return usuarioRepository.findAll();
	}

	@GetMapping("/usuario/{id}")
	public Mono<ResponseEntity<Usuario>> obtenerUsuario(@PathVariable String id) {
		return usuarioRepository.findById(id)
				.map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));

	}

	@GetMapping("/usuario/byEmail/{email}")
	public Mono<ResponseEntity<Usuario>> obtenerEmail(@PathVariable String email) {
		return usuarioRepository.findByEmail(email).map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));

	}

	@PostMapping("/usuario")
	public Mono<ResponseEntity<Usuario>> guardarUsuario(@RequestBody Usuario usuario) {
		return usuarioRepository.save(usuario)
				.map(usuarioGuardado -> new ResponseEntity<>(usuarioGuardado, HttpStatus.ACCEPTED))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE));
	}

	@PostMapping("/login")
	public Mono<AuthResponse> login(@RequestBody AuthRequest authRequest) {
	    Exception ex = new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalido credenciales");
		return usuarioRepository.findByUsuario(authRequest.getUsername(),authRequest.getPassword())
				.filter(user -> passwordEncoder.matches(authRequest.getPassword(), user.getPassword()) )
				.map(user -> new AuthResponse(jwtUtil.generateToken(user)))
				.switchIfEmpty(Mono.error(ex));
	}
	
//	@PostMapping("/login")
//	public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
//		Usuario u = usuarioRepository.findByUsuario(authRequest.getUsername(),authRequest.getPassword());
//		if (u != null) {
//			if (passwordEncoder.encode(authRequest.getPassword()).equals(u.getPassword())) {
//				return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(u)));
//			} else {
//				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//			}
//		} else {
//			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//		}
//	}
	
	@PutMapping("/usuario/{id}")
	public Mono<ResponseEntity<Usuario>> actualizarUsuario(@RequestBody Usuario usuario, @PathVariable String id) {
		return usuarioRepository.findById(id).flatMap(usuarioActualizado -> {
			usuario.setId(id);
			return usuarioRepository.save(usuario).map(usuario1 -> new ResponseEntity<>(usuario1, HttpStatus.ACCEPTED));
		}).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping(value = "/usuario/{id}")
	public Mono<Void> eliminarUsuario(@PathVariable String id) {
		return usuarioRepository.deleteById(id);
	}

	public UsuarioRepository getUsuarioRepository() {
		return usuarioRepository;
	}

	public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	

}

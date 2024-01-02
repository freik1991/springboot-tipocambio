package com.tipocambio.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.tipocambio.bean.Usuario;

import reactor.core.publisher.Mono;

@Repository
public interface  UsuarioRepository  extends ReactiveCrudRepository<Usuario, String>{
	Mono<Usuario> findByUsuario(String usuario,String password);
	Mono<Usuario> findByEmail(String email);

	
}

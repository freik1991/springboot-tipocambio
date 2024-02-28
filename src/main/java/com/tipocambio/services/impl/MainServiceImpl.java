package com.tipocambio.services.impl;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.tipocambio.bean.Usuario;
import com.tipocambio.repository.UsuarioRepository;
import com.tipocambio.security.enums.Role;

public class MainServiceImpl {
	@Autowired
	private UsuarioRepository usuarioRepository;

//	@PostConstruct
//	public void init() {
//		try {
//			Usuario usuario = new Usuario();
//			// usuario.setId(1);
//			usuario.setUsuario("admin");
//			usuario.setNombre("jose");
//			usuario.setApellidos("sosa");
//			usuario.setEmail("jose@hotmail.com");
//			usuario.setClave("dQNjUIMorJb8Ubj2+wVGYp6eAeYkdekqAcnYp+aRq5w=");
//			usuario.setEstado(true);
//			usuario.setRoles(Arrays.asList(Role.ROLE_ADMIN));
//			usuarioRepository.save(usuario);
//			usuario = new Usuario();
//			// usuario.setId(2);
//			usuario.setUsuario("fran12");
//			usuario.setNombre("francisco");
//			usuario.setApellidos("julca");
//			usuario.setEmail("francisco@gmail.com");
//			usuario.setClave("vxSBJJEUroJ/nutiZUW4/6CIxrbXi8ZnrtUhMNgQ5XM=");
//			usuario.setEstado(true);
//			usuario.setRoles(Arrays.asList(Role.ROLE_USER));
//			usuarioRepository.save(usuario);
//			Usuario u = usuarioRepository.findByUsuario("admin");
//			System.out.println("u:" + u.getUsuario());
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//
//	}
}

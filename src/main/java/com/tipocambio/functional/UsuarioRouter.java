//package com.tipocambio.functional;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.reactive.function.server.RouterFunction;
//import org.springframework.web.reactive.function.server.RouterFunctions;
//import org.springframework.web.reactive.function.server.ServerResponse;
//
//import static org.springframework.web.reactive.function.server.RequestPredicates.*;
//@Configuration
//public class UsuarioRouter {
//
//	@Bean
//	public RouterFunction<ServerResponse> routersUsuarios(UsuarioHandler usuarioHandler){
//		return RouterFunctions
//				.route(GET("/functional/usuario/"), usuarioHandler::listarUsuarios)
//				.andRoute(GET("/functional/usuario/{id}"), usuarioHandler::obtenerUsuarioxId)
//				.andRoute(GET("/functional/usuario/byEmail/{email}"), usuarioHandler::obtenerUsuarioxEmail)
//				.andRoute(POST("/functional/usuario/"), usuarioHandler::insertarUsuario)
//				.andRoute(PUT("/functional/usuario/{id}"), usuarioHandler::actualizarUsuario)
//				.andRoute(DELETE("/functional/usuario/{id}"), usuarioHandler::eliminarUsuario);
//
//	}
//}

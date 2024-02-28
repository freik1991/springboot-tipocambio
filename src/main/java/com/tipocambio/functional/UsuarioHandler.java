//package com.tipocambio.functional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.server.ServerRequest;
//import org.springframework.web.reactive.function.server.ServerResponse;
//
//import com.tipocambio.bean.Usuario;
//import com.tipocambio.repository.UsuarioRepository;
//
//import reactor.core.publisher.Mono;
//import static org.springframework.web.reactive.function.BodyInserters.*;
//
//@Component
//public class UsuarioHandler {
//
//	@Autowired
//	private UsuarioRepository usuarioRepository;
//
//	private Mono<ServerResponse> response404 = ServerResponse.notFound().build();
//	private Mono<ServerResponse> response406 = ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).build();
//
//	public Mono<ServerResponse> listarUsuarios(ServerRequest serverRequest) {
//		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(usuarioRepository.findAll(),
//				Usuario.class);
//	}
//
//	public Mono<ServerResponse> obtenerUsuarioxId(ServerRequest request) {
//		String id = request.pathVariable("id");
//
//		return usuarioRepository.findById(id)
//				.flatMap(
//						usuario -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromValue(usuario)))
//				.switchIfEmpty(response404);
//
//	}
//
//	public Mono<ServerResponse> obtenerUsuarioxEmail(ServerRequest request) {
//		String email = request.pathVariable("email");
//
//		return usuarioRepository.findByEmail(email)
//				.flatMap(
//						usuario -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromValue(usuario)))
//				.switchIfEmpty(response404);
//
//	}
//
//	public Mono<ServerResponse> insertarUsuario(ServerRequest request) {
//		Mono<Usuario> usuarioMono = request.bodyToMono(Usuario.class);
//
//		return usuarioMono
//				.flatMap(
//						usuario -> usuarioRepository.save(usuario)
//								.flatMap(usuarioGuardado -> ServerResponse.accepted()
//										.contentType(MediaType.APPLICATION_JSON).body(fromValue(usuarioGuardado))))
//				.switchIfEmpty(response406);
//	}
//
//	public Mono<ServerResponse> actualizarUsuario(ServerRequest request) {
//		Mono<Usuario> usuarioMono = request.bodyToMono(Usuario.class);
//		String id = request.pathVariable("id");
//
//		Mono<Usuario> usuarioActualizado = usuarioMono
//				.flatMap(usuario -> usuarioRepository.findById(id).flatMap(oldUsuario -> {
//					oldUsuario.setNombre(usuario.getNombre());
//					oldUsuario.setApellidos(usuario.getApellidos());
//					oldUsuario.setEmail(usuario.getEmail());
//					return usuarioRepository.save(oldUsuario);
//				}));
//
//		return usuarioActualizado.flatMap(
//				usuario -> ServerResponse.accepted().contentType(MediaType.APPLICATION_JSON).body(fromValue(usuario)))
//				.switchIfEmpty(response406);
//
//	}
//	
//	public Mono<ServerResponse> eliminarUsuario(ServerRequest request){
//		String id = request.pathVariable("id");
//		Mono<Void> usuarioEliminado = usuarioRepository.deleteById(id);
//		
//		return ServerResponse.ok()
//				.contentType(MediaType.APPLICATION_JSON)
//				.body(usuarioEliminado,Void.class);
//	}
//}

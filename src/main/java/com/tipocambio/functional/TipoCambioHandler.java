//package com.tipocambio.functional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.server.ServerRequest;
//import org.springframework.web.reactive.function.server.ServerResponse;
//
//import com.tipocambio.bean.TipoCambio;
//import com.tipocambio.bean.Usuario;
//import com.tipocambio.repository.TipoCambioRepository;
//import com.tipocambio.repository.UsuarioRepository;
//
//import reactor.core.publisher.Mono;
//import static org.springframework.web.reactive.function.BodyInserters.*;
//
//@Component
//public class TipoCambioHandler {
//
//	@Autowired
//	private TipoCambioRepository tipoCambioRepository;
//
//	private Mono<ServerResponse> response404 = ServerResponse.notFound().build();
//	private Mono<ServerResponse> response406 = ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).build();
//
//	public Mono<ServerResponse> listarTipoCambio(ServerRequest serverRequest) {
//		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(tipoCambioRepository.findAll(),
//				Usuario.class);
//	}
//
//
//
//}

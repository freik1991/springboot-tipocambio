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
//public class TipoCambioRouter {
//
//	@Bean
//	public RouterFunction<ServerResponse> routerTipoCambios(TipoCambioHandler tipoCambioHandler){
//		return RouterFunctions
//				.route(GET("/functional/tipocambio/"), tipoCambioHandler::listarTipoCambio);
//
//	}
//}

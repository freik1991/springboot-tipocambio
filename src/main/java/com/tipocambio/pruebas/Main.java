package com.tipocambio.pruebas;

import reactor.core.publisher.Mono;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Mono<String> mono = Mono.just("Hola");

		mono.subscribe(data -> System.out.println(data), err -> System.out.println(err),
				() -> System.out.println("completado!"));

	}

}

package com.tipocambio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;



@SpringBootApplication
public class SpringbootTipocambioApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootTipocambioApplication.class, args);
	}

}

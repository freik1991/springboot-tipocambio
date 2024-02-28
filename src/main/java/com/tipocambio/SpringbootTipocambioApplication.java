package com.tipocambio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.**.security,com.**.controller", "com.**.services" ,"com.**.services.impl", "com.**.repository"})
@EnableJpaRepositories(basePackages={"com.**.security,com.**.controller", "com.**.services" ,"com.**.services.impl", "com.**.repository"})
@EnableTransactionManagement
public class SpringbootTipocambioApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootTipocambioApplication.class, args);
	}

}

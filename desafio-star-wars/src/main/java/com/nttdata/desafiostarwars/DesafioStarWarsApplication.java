package com.nttdata.desafiostarwars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DesafioStarWarsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioStarWarsApplication.class, args);
	}
}

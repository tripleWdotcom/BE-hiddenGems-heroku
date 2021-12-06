package com.group4.auctionsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class AuctionsiteApplication {

	public static void main(String[] args) {
		// const PORT = process.env.PORT || 4000
		String PORT = System.getenv("PORT");

		SpringApplication app = new SpringApplication(AuctionsiteApplication.class);
		app.setDefaultProperties(Collections
				.singletonMap("server.port", PORT == null ? 4000 : PORT));
		app.run(args);
	}

}

package com.example.bandShop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication/*(exclude = {DataSourceAutoConfiguration.class })*/
public class BandShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(BandShopApplication.class, args);
	}

}

package com.SOS.SmartOrderSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class}) // springSecurity 에러 해결용
public class SmartOrderSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartOrderSystemApplication.class, args);
	}

}

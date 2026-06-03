package com.fundoonotes.fundoo_notes;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.TimeZone;

@SpringBootApplication
@EnableScheduling
public class FundooNotesApplication {

	@PostConstruct
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
		System.out.println("Default timezone initialized to Asia/Kolkata (IST)");
	}

	public static void main(String[] args) {
		SpringApplication.run(FundooNotesApplication.class, args);
	}

}

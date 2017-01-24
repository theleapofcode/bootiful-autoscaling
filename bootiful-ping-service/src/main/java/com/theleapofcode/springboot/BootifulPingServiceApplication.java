package com.theleapofcode.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.theleapofcode.springboot.flight.search.stats.TPMCounter;

@SpringBootApplication
public class BootifulPingServiceApplication implements CommandLineRunner {

	@Autowired
	private TPMCounter tpm;

	public static void main(String[] args) {
		SpringApplication.run(BootifulPingServiceApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		tpm.start();
	}

}

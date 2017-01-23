package com.theleapofcode.springboot.lifecyclemanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.theleapofcode.springboot.lifecyclemanager.metricscollector.MetricsCollector;

@EnableDiscoveryClient
@SpringBootApplication
public class LifeCycleManagerApplication implements CommandLineRunner {

	@Autowired
	MetricsCollector metricsCollector;

	public static void main(String[] args) {
		SpringApplication.run(LifeCycleManagerApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		metricsCollector.start();
	}
}
package com.theleapofcode.springboot.lifecyclemanager.metricscollector;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.theleapofcode.springboot.lifecyclemanager.decisionengine.DecisionEngine;

@Component
public class MetricsCollector {

	@Autowired
	private DecisionEngine decisionEngine;

	@Autowired
	private RestTemplate restTemplate;

	public void start() {
		while (true) {
			Map metrics = restTemplate.getForObject("http://localhost:8090/metrics", Map.class);
			decisionEngine.execute("ping", metrics);
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

@Configuration
class AppConfiguration {

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
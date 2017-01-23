package com.theleapofcode.springboot.lifecyclemanager.metricscollector;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.theleapofcode.springboot.lifecyclemanager.decisionengine.DecisionEngine;

@Component
public class MetricsCollector {

	@Autowired
	private DiscoveryClient eurekaClient;

	@Autowired
	private DecisionEngine decisionEngine;

	@Autowired
	private RestTemplate restTemplate;

	public void start() {
		while (true) {
			eurekaClient.getServices().forEach(service -> {
				System.out.println("printing service " + service);
				Map metrics = restTemplate.getForObject("http://" + service + "/metrics", Map.class);
				decisionEngine.execute(service, metrics);
			});
		}
	}
}

@Configuration
class AppConfiguration {

	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
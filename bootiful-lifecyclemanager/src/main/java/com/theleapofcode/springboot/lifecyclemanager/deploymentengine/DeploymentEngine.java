package com.theleapofcode.springboot.lifecyclemanager.deploymentengine;

import org.springframework.stereotype.Component;

import com.theleapofcode.springboot.lifecyclemanager.deploymentrules.DeploymentRule;

@Component
public class DeploymentEngine {
	public static int num_instances = 1;

	public boolean scaleUp(DeploymentRule rule, String serviceId) {
		if (!rule.execute()) {
			return false;
		}
		num_instances++;
		System.out.println("Number of instances - " + num_instances + " :: Kicking off a new instance " + serviceId);

		return true;
	}

	public boolean scaleDown(DeploymentRule rule, String serviceId) {
		if (!rule.execute()) {
			return false;
		}
		num_instances--;
		System.out.println("Number of instances - " + num_instances + " :: Shutting down an instance " + serviceId);

		return true;
	}

}
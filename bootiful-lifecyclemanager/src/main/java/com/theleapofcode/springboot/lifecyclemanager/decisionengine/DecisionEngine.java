package com.theleapofcode.springboot.lifecyclemanager.decisionengine;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.theleapofcode.springboot.lifecyclemanager.deploymentengine.DeploymentEngine;
import com.theleapofcode.springboot.lifecyclemanager.deploymentrules.DeploymentRules;
import com.theleapofcode.springboot.lifecyclemanager.scalingpolicy.ScalingPolicies;

@Component
public class DecisionEngine {

	@Autowired
	ScalingPolicies scalingPolicies;

	@Autowired
	DeploymentEngine deploymentEngine;

	@Autowired
	DeploymentRules deploymentRules;

	public boolean execute(String serviceId, Map metrics) {
		int result = scalingPolicies.getPolicy(serviceId).execute(serviceId, metrics);
		if (result == 1) {
			return deploymentEngine.scaleUp(deploymentRules.getDeploymentRules(serviceId), serviceId);
		} else if (result == -1) {
			return deploymentEngine.scaleDown(deploymentRules.getDeploymentRules(serviceId), serviceId);
		}
		return false;
	}
}
package com.theleapofcode.springboot.lifecyclemanager.deploymentrules;

import com.theleapofcode.springboot.lifecyclemanager.deploymentengine.DeploymentEngine;

public class DummyDeploymentRule implements DeploymentRule {
	private static final int max_instance = 3;
	private static final int min_instance = 0;

	public boolean execute() {
		if (DeploymentEngine.num_instances < max_instance && DeploymentEngine.num_instances > min_instance)
			return true;
		return false;
	}
}
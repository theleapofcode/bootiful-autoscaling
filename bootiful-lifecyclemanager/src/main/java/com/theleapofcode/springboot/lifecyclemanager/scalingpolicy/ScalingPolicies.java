package com.theleapofcode.springboot.lifecyclemanager.scalingpolicy;

import org.springframework.stereotype.Component;

@Component
public class ScalingPolicies {
	public ScalingPolicy getPolicy(String serviceId) {
		return new TpmScalingPolicy();
	}
}
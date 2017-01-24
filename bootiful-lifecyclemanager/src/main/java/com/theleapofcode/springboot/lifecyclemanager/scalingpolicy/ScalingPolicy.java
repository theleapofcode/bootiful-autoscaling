package com.theleapofcode.springboot.lifecyclemanager.scalingpolicy;

import java.util.Map;

public interface ScalingPolicy {
	public int execute(String serviceId, Map metrics);
}
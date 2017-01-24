package com.theleapofcode.springboot.lifecyclemanager.scalingpolicy;

import java.util.Map;

public class TpmScalingPolicy implements ScalingPolicy {
	public int execute(String serviceId, Map metrics) {
		if (metrics.containsKey("gauge.tpm")) {
			Double tpm = (Double) metrics.get("gauge.tpm");
			System.out.println("gauge.tpm " + tpm);
			if (tpm > 10)
				return 1;
			else if (tpm < 2)
				return -1;
			else
				return 0;
		}
		return 0;
	}
}
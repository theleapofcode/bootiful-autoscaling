package com.theleapofcode.springboot.flight.search.stats;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.stereotype.Component;

@Component
public class TPMCounter {

	LongAdder count;
	Calendar expiry = null;
	GaugeService gaugeService;

	@Autowired
	public TPMCounter(GaugeService gaugeService) {
		this.gaugeService = gaugeService;
		reset();
	}

	public void reset() {
		gaugeService.submit("tpm", 0);
		count = new LongAdder();
		expiry = Calendar.getInstance();
		expiry.add(Calendar.MINUTE, 1);
	}

	public boolean isExpired() {
		return Calendar.getInstance().after(expiry);
	}

	public void increment() {
		if (isExpired()) {
			reset();
		}
		gaugeService.submit("tpm", count.intValue());
		count.increment();
	}

	public void start() throws InterruptedException {

		while (true) {
			if (isExpired()) {
				reset();
			}
			gaugeService.submit("tpm", count.intValue());
			TimeUnit.MINUTES.sleep(1);
		}

	}

}

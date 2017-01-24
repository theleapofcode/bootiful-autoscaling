package com.theleapofcode.springboot.flight.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.theleapofcode.springboot.flight.search.stats.TPMCounter;

@CrossOrigin
@RestController
class PingRestController {

	@Autowired
	private TPMCounter tpm;

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	String ping() {
		tpm.increment();
		return "PONG";
	}

}

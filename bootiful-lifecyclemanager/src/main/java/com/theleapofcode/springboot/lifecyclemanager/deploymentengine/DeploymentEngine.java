package com.theleapofcode.springboot.lifecyclemanager.deploymentengine;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.theleapofcode.springboot.lifecyclemanager.deploymentrules.DeploymentRule;

@RefreshScope
@Component
public class DeploymentEngine {
	public static int num_instances = 0;

	@Value("${jsch.user}")
	private String user;

	@Value("${jsch.password}")
	private String password;

	@Value("${jsch.host}")
	private String host;

	@Value("${jsch.command}")
	private String command;

	public boolean scaleUp(DeploymentRule rule, String serviceId) {
		if (!rule.execute()) {
			return false;
		}
		num_instances++;
		Runnable runnable = () -> {
			System.out.println("Kicking off a new instance " + serviceId);
			executeSSH();
		};

		Thread thread = new Thread(runnable);
		thread.start();

		return true;
	}

	private boolean executeSSH() {
		// get deployment descriptor, instead of this hard coded.
		// or execute a script on the target machine which download artifact
		// from nexus
		try {
			System.out.println("Executing " + command);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			JSch jsch = new JSch();
			Session session = jsch.getSession(user, host, 22);
			session.setPassword(password);

			session.setConfig(config);
			session.connect();
			System.out.println("Connected");

			ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
			InputStream in = channelExec.getInputStream();
			channelExec.setCommand(command);
			channelExec.connect();

			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			int index = 0;

			while ((line = reader.readLine()) != null) {
				System.out.println(++index + " : " + line);
			}
			channelExec.disconnect();
			session.disconnect();

			System.out.println("Done!");

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	boolean scaleDown() {
		// NOT IMPLEMENTED. THIS WILL USE SPRING BOOT ACTUATOR
		return true;
	}

}
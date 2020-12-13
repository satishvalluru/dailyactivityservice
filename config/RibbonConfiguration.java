package com.java.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;

public class RibbonConfiguration {
	
	@Autowired
	IClientConfig config;
	
	@Bean
	public IRule ribbonRule(IClientConfig config) {
		return new BestAvailableRule();
	}

	@Bean
	public IPing ribbonPing(IClientConfig config) {
		return new PingUrl();
	}


}

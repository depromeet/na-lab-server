package me.nalab.core.authorization.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.nalab.core.authorization.api.Authorizer;
import me.nalab.core.authorization.engine.DefaultAuthorizerEngine;

@Configuration
public class Configurer {

	@Bean
	Authorizer defaultAuthorizerEngine() {
		return new DefaultAuthorizerEngine();
	}

}

package me.nalab.auth.application.common.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import me.nalab.auth.application.common.property.JwtProperties;

@Configuration
@EnableConfigurationProperties({
	JwtProperties.class
})
public class PropertyConfig {
}

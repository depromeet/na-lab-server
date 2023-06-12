package me.nalab.core.secure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
class CorsConfig implements WebMvcConfigurer {
	private static final String[] ALLOWED_METHOD_NAMES = new String[] {"GET", "HEAD", "POST", "PUT", "DELETE", "TRACE",
		"OPTIONS", "PATCH"};

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("*")
			.allowedMethods(ALLOWED_METHOD_NAMES)
			.maxAge(3600);
	}
}

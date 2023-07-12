package me.nalab.core.secure.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
class CorsConfig implements WebMvcConfigurer {
	private static final String[] ALLOWED_METHOD_NAMES = new String[] {"GET", "HEAD", "POST", "PUT", "DELETE", "TRACE",
		"OPTIONS", "PATCH"};

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		addCorsMappingsV1(registry);
		addCorsMappingsV2(registry);
		addCorsMappingsMock(registry);
	}

	private void addCorsMappingsV1(CorsRegistry corsRegistry) {
		corsRegistry.addMapping("/v1/**")
			.allowedOrigins("*")
			.allowedHeaders("*")
			.allowedMethods(ALLOWED_METHOD_NAMES)
			.maxAge(3600);
	}

	private CorsRegistration addCorsMappingsV2(CorsRegistry corsRegistry) {
		return corsRegistry.addMapping("/v2/**")
			.allowedOrigins("*")
			.allowedHeaders("*")
			.allowedMethods(ALLOWED_METHOD_NAMES)
			.maxAge(3600);
	}

	private CorsRegistration addCorsMappingsMock(CorsRegistry corsRegistry) {
		return corsRegistry.addMapping("/mock/**")
			.allowedOrigins("*")
			.allowedHeaders("*")
			.allowedMethods(ALLOWED_METHOD_NAMES)
			.maxAge(3600);
	}

}

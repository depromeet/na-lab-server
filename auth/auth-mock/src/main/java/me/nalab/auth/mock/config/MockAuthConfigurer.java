package me.nalab.auth.mock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import me.nalab.auth.mock.interceptor.MockAuthInterceptor;

@Configuration
public class MockAuthConfigurer implements WebMvcConfigurer {

	private static final String[] INTERCEPTOR_URLS = {
		"/v1/surveys",
		"/v1/users",
		"/v1/surveys-id",
		"/v1/questions",
		"/v1/feedbacks/*",
		"/v1/feedbacks",
		"/v1/reviewers*",
		"/v1/reviewers/summary*",
		"/v2/surveys/*/feedbacks",
	};

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(mockAuthInterceptor())
			.addPathPatterns(INTERCEPTOR_URLS);
	}

	@Bean
	HandlerInterceptor mockAuthInterceptor() {
		return new MockAuthInterceptor();
	}

}

package me.nalab.auth.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import me.nalab.auth.application.port.in.web.TargetIdGetPort;

@Configuration
public class JwtDecryptInterceptorConfigurer implements WebMvcConfigurer {

	@Autowired
	private TargetIdGetPort targetIdGetPort;

	private static final String[] INTERCEPTOR_URLS = {
		"/v1/surveys",
		"/v1/surveys/exists",
		"/v1/surveys-id",
		"/v1/users",
		"/v1/questions",
		"/v1/feedbacks/*",
		"/v1/feedbacks",
		"/v1/reviewers*",
		"/v1/reviewers/summary*",
		"/v2/surveys/*/feedbacks",
		"/v1/feedbacks/bookmarks",
		"/v1/users",
		"/v1/gallerys/previews",
		"/v1/surveys/*/bookmarks",
	};

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtDecryptInterceptor())
			.addPathPatterns(INTERCEPTOR_URLS);
	}

	@Bean
	JwtDecryptInterceptor jwtDecryptInterceptor() {
		return new JwtDecryptInterceptor(targetIdGetPort);
	}

}

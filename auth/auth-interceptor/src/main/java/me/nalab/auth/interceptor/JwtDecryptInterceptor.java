package me.nalab.auth.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import me.nalab.auth.application.port.in.web.TargetIdGetPort;

public class JwtDecryptInterceptor implements HandlerInterceptor {

	private final TargetIdGetPort targetIdGetPort;

	JwtDecryptInterceptor(TargetIdGetPort targetIdGetPort) {
		this.targetIdGetPort = targetIdGetPort;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		if(!isExcludedURI(request)) {
			String token = request.getHeader("Authorization");
			throwIfCannotValidToken(token);
			Long targetId = targetIdGetPort.getTargetId(token);
			request.setAttribute("logined", targetId);
		}
		return true;
	}

	private boolean isExcludedURI(HttpServletRequest httpServletRequest) {
		return httpServletRequest.getMethod().equals("POST") && httpServletRequest.getRequestURI()
			.contains("/v1/feedbacks");
	}

	private void throwIfCannotValidToken(String token) {
		if(token == null) {
			throw new CannotValidMockTokenException();
		}
	}

}

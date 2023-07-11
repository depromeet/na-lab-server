package me.nalab.auth.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import me.nalab.auth.application.port.in.web.TargetIdGetPort;

public class JwtDecryptInterceptor implements HandlerInterceptor {

	private static final String[][] EXCLUDED_URI_LIST = new String[][] {
		{"POST", "/v1/feedbacks"},
		{"GET", "/v1/feedbacks/bookmarks"},
		{"GET", "/v1/users"}
	};
	private final TargetIdGetPort targetIdGetPort;

	JwtDecryptInterceptor(TargetIdGetPort targetIdGetPort) {
		this.targetIdGetPort = targetIdGetPort;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		if (isPreflight(request)) {
			return true;
		}
		if (!isExcludedURI(request)) {
			String token = request.getHeader("Authorization");
			throwIfCannotValidToken(token);
			Long targetId = targetIdGetPort.getTargetId(token.split(" ")[1]);
			request.setAttribute("logined", targetId);
		}
		return true;
	}

	private boolean isPreflight(HttpServletRequest request) {
		return request.getMethod().equals("OPTIONS");
	}

	private boolean isExcludedURI(HttpServletRequest httpServletRequest) {
		String httpMethod = httpServletRequest.getMethod();
		String requestURI = httpServletRequest.getRequestURI();

		for (String[] excludedURI : EXCLUDED_URI_LIST) {
			if (excludedURI[0].equals(httpMethod) && excludedURI[1].equals(requestURI)) {
				return true;
			}
		}

		return false;
	}

	private void throwIfCannotValidToken(String token) {
		if (token == null) {
			throw new CannotValidMockTokenException();
		}
	}

}

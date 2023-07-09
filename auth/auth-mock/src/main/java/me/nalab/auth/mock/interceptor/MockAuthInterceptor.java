package me.nalab.auth.mock.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.event.EventListener;
import org.springframework.web.servlet.HandlerInterceptor;

import me.nalab.auth.mock.api.MockUserRegisterEvent;

public class MockAuthInterceptor implements HandlerInterceptor {

	private static final String[][] EXCLUDED_URI_LIST = new String[][] {
			{"POST", "/v1/feedbacks"},
			{"GET", "/v1/feedbacks/bookmarks"},
	};
	private String expectedToken = null;
	private Long expectedId = null;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		if(!isExcludedURI(request)) {
			String token = request.getHeader("Authorization");
			throwIfCannotValidToken(token);
			request.setAttribute("logined", expectedId);
		}
		return true;
	}

	private boolean isExcludedURI(HttpServletRequest httpServletRequest) {
		String httpMethod = httpServletRequest.getMethod();
		String requestURI = httpServletRequest.getRequestURI();

		for (String[] excludedURI: EXCLUDED_URI_LIST) {
			if (excludedURI[0].equals(httpMethod) && excludedURI[1].contains(requestURI)) {
				return true;
			}
		}

		return false;
	}

	private void throwIfCannotValidToken(String token) {
		if(expectedToken == null || expectedId == null || !expectedToken.equals(token)) {
			throw new CannotValidMockTokenException();
		}
	}

	@EventListener(MockUserRegisterEvent.class)
	void mockUserRegister(MockUserRegisterEvent mockUserRegisterEvent) {
		expectedToken = mockUserRegisterEvent.getExpectedToken();
		expectedId = mockUserRegisterEvent.getExpectedId();
	}

}

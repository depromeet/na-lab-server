package me.nalab.auth.mock.interceptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.of;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ContextConfiguration;

import me.nalab.auth.mock.api.MockUserRegisterEvent;
import me.nalab.auth.mock.config.MockAuthConfigurer;

@WebMvcTest
@ContextConfiguration(classes = {MockAuthConfigurer.class, MockAuthInterceptorTestController.class})
class MockAuthInterceptorTest extends AbstractMockAuthTest {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@ParameterizedTest
	@MethodSource("successSources")
	@DisplayName("Mock 로그인 성공 테스트")
	void SUCCESS_LOGIN(HttpMethod httpMethod, String url, String expectedToken, Long expectedId) throws
		Exception {
		// given
		applicationEventPublisher.publishEvent(MockUserRegisterEvent.builder()
			.expectedId(expectedId)
			.expectedToken(expectedToken)
			.build()
		);

		// when
		Long id = call(httpMethod, url, expectedToken);

		// then
		assertEquals(expectedId, id);
	}

	@ParameterizedTest
	@MethodSource("failSources")
	@DisplayName("Mock 로그인 실패 테스트")
	void FAIL_LOGIN(HttpMethod httpMethod, String url, String requestToken, String expectedToken,
		Long expectedId) {
		// given
		applicationEventPublisher.publishEvent(MockUserRegisterEvent.builder()
			.expectedId(expectedId)
			.expectedToken(expectedToken)
			.build()
		);

		// when & then
		assertThrows(Exception.class, () -> call(httpMethod, url, requestToken));
	}

	static Stream<Arguments> successSources() {
		return Stream.of(
			of(HttpMethod.POST, "/v1/surveys", "token1", 1L)
			, of(HttpMethod.GET, "/v1/users", "token2", 2L)
			, of(HttpMethod.GET, "/v1/surveys-id", "token3", 3L)
			, of(HttpMethod.GET, "/v1/questions?survey-id=4", "token4", 4L)
			, of(HttpMethod.GET, "/v1/feedbacks?survey-id=5", "token5", 5L)
			, of(HttpMethod.GET, "/v1/feedbacks/6", "token6", 6L)
			, of(HttpMethod.GET, "/v1/feedbacks/summary?survey-id=7", "token7", 7L)
			, of(HttpMethod.GET, "/v1/feedbacks?question-id=8", "token8", 8L)
		);
	}

	static Stream<Arguments> failSources() {
		return Stream.of(
			of(HttpMethod.POST, "/v1/surveys", null, null, null)
			, of(HttpMethod.GET, "/v1/users", null, "token2", 2L)
			, of(HttpMethod.GET, "/v1/users", "token3", null, 3L)
			, of(HttpMethod.GET, "/v1/users", "token4", "4nekot", 4L)
			, of(HttpMethod.GET, "/v1/users", "token4", "token4", null)
		);
	}

}

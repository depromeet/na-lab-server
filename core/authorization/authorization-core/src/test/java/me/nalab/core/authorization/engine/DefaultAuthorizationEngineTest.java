package me.nalab.core.authorization.engine;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import me.nalab.core.authorization.api.Authorizer;
import me.nalab.core.authorization.validator.LongValidorFactory;
import me.nalab.core.authorization.validator.StringValidatorFactory;

class DefaultAuthorizationEngineTest {

	private final Authorizer authorizationEngine = new DefaultAuthorizerEngine();
	private final StringValidatorFactory stringValidatorFactory = new StringValidatorFactory();
	private final LongValidorFactory longValidorFactory = new LongValidorFactory();

	@ParameterizedTest
	@MethodSource("stringSources")
	void AUTHORIZATION_WITH_STRING_VALIDATOR_FACTORY(String expected, String target, boolean expectedResult) {
		// when
		boolean result = catchException(
			() -> authorizationEngine.authorization(expected, target, stringValidatorFactory)) == null;

		// then
		assertThat(result).isEqualTo(expectedResult);
	}

	@ParameterizedTest
	@MethodSource("longSources")
	void AUTHORIZATION_WITH_LONG_VALIDATOR_FACTORY(Long expected, Long target, boolean expectedResult) {
		// when
		boolean result = catchException(
			() -> authorizationEngine.authorization(expected, target, longValidorFactory)) == null;

		// then
		assertThat(result).isEqualTo(expectedResult);
	}

	private static Stream<Arguments> stringSources() {
		return Stream.of(
			Arguments.of("hello", "hello", true), // 값이 같다면 성공한다.
			Arguments.of("", "", true), // 같은 값의 Empty String이 들어오면 성공한다.
			Arguments.of(" ", " ", true), // 같은 값의 Blank String이 들어오면 성공한다.

			Arguments.of("hello", "world", false), // 값이 다르다면 실패한다.
			Arguments.of("", " ", false), // 다른 값의 Blank, Empty String이 들어오면 실패한다.
			Arguments.of(null, "hello", false), // expected로 null 값이 들어온다면 실패한다.
			Arguments.of("hello", null, false), // target으로 null 값이 들어온다면 실패한다.
			Arguments.of(null, null, false) // expected와 target으로 null이 들어오면 실패한다.
		);
	}

	private static Stream<Arguments> longSources() {
		return Stream.of(
			Arguments.of(0L, 0L, true), // 값이 같다면 성공한다.

			Arguments.of(1L, 2L, false), // 값이 다르다면 실패한다.
			Arguments.of(null, 2L, false), // expected로 null 값이 들어온다면 실패한다.
			Arguments.of(1L, null, false), // target으로 null 값이 들어온다면 실패한다.
			Arguments.of(null, null, false) // expected와 target으로 null이 들어오면 실패한다.
		);
	}

}

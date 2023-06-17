package me.nalab.core.authorization.spi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import me.nalab.core.authorization.api.ParameterReference;
import me.nalab.core.authorization.validator.LongValidorFactory;
import me.nalab.core.authorization.validator.StringValidatorFactory;

class AuthorizationSystemTest {

	@Test
	@DisplayName("String valid String 타입 추론 및 검증 테스트")
	void EXTRACT_TYPE_STRING_VALID_STRING() {
		// given
		String expected = "hello";
		String parameter = "hello";
		ValidatorFactory<?, ?> validatorFactory = new StringValidatorFactory();
		Validator validator = validatorFactory.validator();
		ParameterExtractor extractor = validatorFactory.parameterExtractor();
		ParameterReference parameterReference = extractor.extract(parameter);

		// when
		boolean result = validator.valid(expected, parameterReference.value(parameter.getClass()));

		// then
		assertThat(result).isTrue();
	}

	@Test
	@DisplayName("String valid Long 타입 추론 및 검증 실패 테스트")
	void EXTRACT_TYPE_STRING_VALID_LONG() {
		// given
		String expected = "hello";
		Long parameter = 1L;
		ValidatorFactory<?, ?> stringValidatorFactory = new StringValidatorFactory();
		ValidatorFactory<?, ?> longValidatorFactory = new LongValidorFactory();
		Validator validator = stringValidatorFactory.validator();
		ParameterExtractor extractor = longValidatorFactory.parameterExtractor();
		ParameterReference parameterReference = extractor.extract(parameter);

		// when
		Throwable result = catchException(
			() -> validator.valid(expected, parameterReference.value(parameter.getClass())));

		// then
		assertThat(result).isInstanceOf(IllegalStateException.class);
	}

}

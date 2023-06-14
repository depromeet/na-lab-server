package me.nalab.core.authorization.engine;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParameterReferenceTest {

	@Test
	@DisplayName("wrapping과 unwrapping에 같은 type의 parameter가 들어오면 성공한다.")
	void PARAMETER_REFERENCE_SUCCESS() {
		// given
		String parameter = "hello";
		ParameterReference parameterReference = ParameterReference.createInstance(parameter);

		// when
		String result = parameterReference.value(String.class);

		// then
		assertThat(result).isEqualTo(parameter);
	}

	@Test
	@DisplayName("wrapping과 unwrapping에 다른 type이 들어오면 실패한다.")
	void PARAMETER_REFERENCE_FAIL_DIFF_TYPE() {
		// given
		String parameter = "hello";
		ParameterReference parameterReference = ParameterReference.createInstance(parameter);

		// when
		Throwable result = catchThrowable(() -> parameterReference.value(Long.class));

		// then
		assertThat(result.getClass()).isEqualTo(WrongReferenceTypeException.class);
	}

}

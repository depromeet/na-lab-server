package me.nalab.survey.application.service.authorization;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchException;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import me.nalab.core.authorization.api.ParameterReference;
import me.nalab.survey.application.port.out.persistence.authorization.TargetIdFindPort;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {LongParameterExtractor.class, SurveyIdValidator.class,
	SurveyIdValidatorFactory.class})
class SurveyIdValidatorTest {

	@Autowired
	private SurveyIdValidatorFactory surveyIdValidatorFactory;

	@MockBean
	private TargetIdFindPort targetIdFindPort;

	@Test
	@DisplayName("targetId와 targetId가 갖고있는 surveyId가 들어왔을때, 검증에 성공한다.")
	void VALID_SUCCESS() {
		// given
		SurveyIdValidator surveyIdValidator = surveyIdValidatorFactory.validator();
		LongParameterExtractor longParameterExtractor = surveyIdValidatorFactory.parameterExtractor();
		Long targetId = 1L;
		Long surveyId = 2L;
		ParameterReference parameterReference = longParameterExtractor.extract(surveyId);

		when(targetIdFindPort.findTargetIdBySurveyId(surveyId)).thenReturn(Optional.of(1L));

		// when
		boolean result = surveyIdValidator.valid(targetId, parameterReference.value(Long.class));

		// then
		assertThat(result).isTrue();
	}

	@Test
	@DisplayName("targetId와 targetId가 갖고있지 않은 surveyId가 들어왔을때, 검증에 실패한다.")
	void VALID_FAIL() {
		// given
		SurveyIdValidator surveyIdValidator = surveyIdValidatorFactory.validator();
		LongParameterExtractor longParameterExtractor = surveyIdValidatorFactory.parameterExtractor();
		Long targetId = 1L;
		Long surveyId = 2L;
		ParameterReference parameterReference = longParameterExtractor.extract(surveyId);

		when(targetIdFindPort.findTargetIdBySurveyId(surveyId)).thenReturn(Optional.of(3L));

		// when
		boolean result = surveyIdValidator.valid(targetId, parameterReference.value(Long.class));

		// then
		assertThat(result).isFalse();
	}

	@Test
	@DisplayName("expected로 Long타입이 아닌 타입이 들어오면, 예외가 던져진다.")
	void WRONG_EXPECTED_TYPE() {
		// given
		SurveyIdValidator surveyIdValidator = surveyIdValidatorFactory.validator();
		LongParameterExtractor longParameterExtractor = surveyIdValidatorFactory.parameterExtractor();
		String targetId = "1L";
		Long surveyId = 2L;
		ParameterReference parameterReference = longParameterExtractor.extract(surveyId);

		// when
		Throwable result = catchException(
			() -> surveyIdValidator.valid(targetId, parameterReference.value(Long.class)));

		// then
		assertThat(result.getClass()).isEqualTo(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("target으로 Long타입이 아닌 타입이 들어오면, 예외가 던져진다.")
	void WRONG_TARGET_TYPE() {
		// given
		LongParameterExtractor longParameterExtractor = surveyIdValidatorFactory.parameterExtractor();
		String surveyId = "2L";

		// when
		Throwable result = catchException(() -> longParameterExtractor.extract(surveyId));

		// then
		assertThat(result.getClass()).isEqualTo(IllegalArgumentException.class);
	}

}

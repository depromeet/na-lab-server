package me.nalab.survey.application.service.authorization;

import java.util.Optional;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import me.nalab.survey.application.exception.FormQuestionFeedbackNotExistException;
import me.nalab.survey.application.port.out.persistence.authorization.TargetIdFindPort;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {FormQuestionFeedbackIdValidator.class, LongParameterExtractor.class,
	FormQuestionFeedbackIdValidatorFactory.class})
class FormQuestionFeedbackIdValidatorTest {

	@Autowired
	private FormQuestionFeedbackIdValidatorFactory feedbackIdValidatorFactory;

	@MockBean
	private TargetIdFindPort targetIdFindPort;

	@ParameterizedTest
	@DisplayName("expectedTargetId와 requestFormQuestionFeedbackId가 같다면, true를 반환한다")
	@MethodSource("authorizationSources")
	void FORM_QUESTION_FEEDBACK_ID_AUTHORIZATION_TEST(Long expectedTargetId, Long requestFormQuestionFeedbackId,
		Long realTargetId, boolean expectedResult) {
		// given
		FormQuestionFeedbackIdValidator formQuestionFeedbackIdValidator = feedbackIdValidatorFactory.validator();
		feedbackIdValidatorFactory.parameterExtractor();

		Mockito.when(targetIdFindPort.findTargetIdByFormQuestionFeedbackId(requestFormQuestionFeedbackId))
			.thenReturn(Optional.of(realTargetId));

		// when
		boolean result = formQuestionFeedbackIdValidator.valid(expectedTargetId, requestFormQuestionFeedbackId);

		// then
		Assertions.assertThat(result).isEqualTo(expectedResult);
	}

	@Test
	@DisplayName("requestFormQuestionFeedbackId에 해당하는 targetId가 없다면, FOrmQuestionFeedbackDoesNotExist 예외를 던진다")
	void THROW_FORM_QUESTION_FEEDBACK_DOES_NOT_EXIST_EXCEPTION() {
		// given
		Long expectedTargetId = 1L;
		Long requestFormQuestionFeedbackId = 1L;
		FormQuestionFeedbackIdValidator formQuestionFeedbackIdValidator = feedbackIdValidatorFactory.validator();
		feedbackIdValidatorFactory.parameterExtractor();

		Mockito.when(targetIdFindPort.findTargetIdByFormQuestionFeedbackId(requestFormQuestionFeedbackId))
			.thenReturn(Optional.empty());

		// when
		Throwable throwable = Assertions.catchThrowable(
			() -> formQuestionFeedbackIdValidator.valid(expectedTargetId, requestFormQuestionFeedbackId));

		// then
		Assertions.assertThat(throwable.getClass()).isEqualTo(FormQuestionFeedbackNotExistException.class);
	}

	@Test
	@DisplayName("expectedTargetId로 Long이 아닌 타입이 들어오면 IllegalArgumentException을 던진다")
	void THROW_ILLEGAL_ARGUMENT_EXCEPTION_WHEN_WRONG_TYPE_ON_EXPECTED_TARGET_ID() {
		// given
		String expectedTargetId = "1L";
		Long requestFormQuestionFeedbackId = 1L;
		FormQuestionFeedbackIdValidator formQuestionFeedbackIdValidator = feedbackIdValidatorFactory.validator();
		feedbackIdValidatorFactory.parameterExtractor();

		// when
		Throwable throwable = Assertions.catchThrowable(
			() -> formQuestionFeedbackIdValidator.valid(expectedTargetId, requestFormQuestionFeedbackId));

		// then
		Assertions.assertThat(throwable.getClass()).isEqualTo(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("requestFormQuestionFeedbackId로 Long이 아닌 타입이 들어오면 IllegalArgumentException을 던진다")
	void THROW_ILLEGAL_ARGUMENT_EXCEPTION_WHEN_WRONG_TYPE_ON_REQUEST_ID() {
		// given
		Long expectedTargetId = 1L;
		String requestFormQuestionFeedbackId = "1L";
		FormQuestionFeedbackIdValidator formQuestionFeedbackIdValidator = feedbackIdValidatorFactory.validator();

		// when
		Throwable throwable = Assertions.catchThrowable(
			() -> formQuestionFeedbackIdValidator.valid(expectedTargetId, requestFormQuestionFeedbackId));

		// then
		Assertions.assertThat(throwable.getClass()).isEqualTo(IllegalArgumentException.class);
	}

	private static Stream<Arguments> authorizationSources() {
		return Stream.of(
			Arguments.of(1L, 1L, 1L, true),
			Arguments.of(1L, 1L, 2L, false)
		);
	}

}

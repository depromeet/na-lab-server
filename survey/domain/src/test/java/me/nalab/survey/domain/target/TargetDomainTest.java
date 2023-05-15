package me.nalab.survey.domain.target;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Logger;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import me.nalab.survey.domain.AbstractTargetSources;
import me.nalab.survey.domain.survey.FormQuestionable;
import me.nalab.survey.domain.survey.Survey;

class TargetDomainTest extends AbstractTargetSources {

	private static final Logger LOGGER = Logger.getLogger(TargetDomainTest.class.getSimpleName());

	@ParameterizedTest
	@MethodSource("targetCreateSuccessSources")
	void CREATE_TARGET_SUCCESS(Function<List<Survey>, Target> targetCreate,
		Function<Supplier<List<FormQuestionable>>, Survey> surveyCreate,
		Supplier<List<FormQuestionable>> formQuestionSupplier) {

		assertDoesNotThrow(() -> targetCreate.apply(
				List.of(
					surveyCreate.apply(formQuestionSupplier)
				)
			)
		);
	}

	@ParameterizedTest
	@MethodSource("targetCreateSuccessSources")
	void TARGET_TO_STRING_LOGGING_SUCCESS(Function<List<Survey>, Target> targetCreate,
		Function<Supplier<List<FormQuestionable>>, Survey> surveyCreate,
		Supplier<List<FormQuestionable>> formQuestionSupplier) {

		Target target = targetCreate.apply(
			List.of(
				surveyCreate.apply(formQuestionSupplier)
			)
		);
		assertNotNull(target);
		LOGGER.info(target.toString());
	}

}

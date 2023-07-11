package me.nalab.survey.domain.target;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.function.Function;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import java.util.logging.Logger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import me.nalab.survey.domain.AbstractTargetSources;
import me.nalab.survey.domain.exception.IdAlreadyGeneratedException;
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

	@Test
	@DisplayName("Target id 생성 성공 테스트")
	void TARGET_WITH_ID_SUCCESS() {
		// given
		LongSupplier longSupplier = () -> 119L;
		Target target = Target.builder()
			.nickname("xb")
			.position("designer")
			.build();

		// when
		target.withId(longSupplier);

		// then
		assertEquals(longSupplier.getAsLong(), target.getId());
	}

	@Test
	@DisplayName("Target id 생성 실패 테스트 - Id 중복 생성 요청")
	void TARGET_WITH_ID_FAIL_DUPLICATED_REQUEST() {
		// given
		LongSupplier longSupplier = () -> 119L;
		Target target = Target.builder()
			.nickname("xb")
			.position("developer")
			.build();

		// when
		target.withId(longSupplier);

		// then
		assertThrows(IdAlreadyGeneratedException.class, () -> target.withId(longSupplier));
	}

	@Test
	@DisplayName("Target position 수정 테스트")
	void TARGET_POSITION_UPDATE_TEST() {

		Target target = Target.builder()
			.nickname("target")
			.position("designer")
			.build();
		String newPosition = "developer";

		target.setPosition(newPosition);

		assertEquals(newPosition, target.getPosition());

	}

}

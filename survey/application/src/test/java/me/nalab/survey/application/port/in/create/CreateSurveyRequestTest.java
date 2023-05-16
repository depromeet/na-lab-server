package me.nalab.survey.application.port.in.create;

import static me.nalab.survey.application.fixture.CreateSurveyFixture.getChoiceQuestionRequest;
import static me.nalab.survey.application.fixture.CreateSurveyFixture.getChoiceRequest;
import static me.nalab.survey.application.fixture.CreateSurveyFixture.getShortQuestionRequest;
import static me.nalab.survey.application.fixture.CreateSurveyFixture.getSurveyCreateRequest;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.of;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import me.nalab.core.idgenerator.idcore.IdGenerator;
import me.nalab.survey.application.TestIdGenerator;
import me.nalab.survey.application.port.in.create.request.CreateSurveyRequest;
import me.nalab.survey.application.port.in.create.request.CreateQuestionable;
import me.nalab.survey.domain.survey.ChoiceFormQuestion;
import me.nalab.survey.domain.survey.FormQuestionable;
import me.nalab.survey.domain.survey.ShortFormQuestion;

class CreateSurveyRequestTest {

	private static final IdGenerator ID_GENERATOR = new TestIdGenerator();

	@ParameterizedTest
	@MethodSource("createSurveyRequestSources")
	void CREATE_SURVEY_SUCCESS(Supplier<CreateSurveyRequest> surveyCreateRequestSupplier) {
		assertDoesNotThrow(surveyCreateRequestSupplier::get);
	}

	@ParameterizedTest
	@MethodSource("createConcreteFormSources")
	void CONVERT_TO_CONCRETE_CLASS_SUCCESS(CreateQuestionable<?> questionable, Class<? extends FormQuestionable> type) {
		assertEquals(questionable.getConcreteQuestion(ID_GENERATOR).getClass(), type);
	}

	private static Stream<Arguments> createSurveyRequestSources() {
		return Stream.of(
			of((Supplier<Object>)() -> getSurveyCreateRequest(0, List.of())),
			of((Supplier<Object>)() -> getSurveyCreateRequest(0, null)),
			of((Supplier<Object>)() -> getSurveyCreateRequest(
				3, List.of(
					getChoiceQuestionRequest("hello-world1", 3, 1, List.of(
						getChoiceRequest(1, "first"),
						getChoiceRequest(2, "second"),
						getChoiceRequest(3, "third")
					)),
					getShortQuestionRequest(2, "hello-world1"),
					getShortQuestionRequest(3, "hello-world2")
				)
			)),
			of((Supplier<Object>)() -> getSurveyCreateRequest(
				2, List.of(
					getChoiceQuestionRequest("hello-world2", 2, 1, List.of(
						getChoiceRequest(1, "first"),
						getChoiceRequest(2, "second"),
						getChoiceRequest(3, "third")
					)),
					getChoiceQuestionRequest("hello-world3", 3, 2, List.of(
						getChoiceRequest(1, "first"),
						getChoiceRequest(2, "second"),
						getChoiceRequest(3, "third")
					))
				)
			)),
			of((Supplier<Object>)() -> getSurveyCreateRequest(
				2, List.of(
					getShortQuestionRequest(2, "hello-world1"),
					getShortQuestionRequest(3, "hello-world2")
				)
			))
		);
	}

	private static Stream<Arguments> createConcreteFormSources() {
		return Stream.of(
			of(getChoiceQuestionRequest("hello-world1", 3, 1, List.of(
					getChoiceRequest(1, "first"),
					getChoiceRequest(2, "second"),
					getChoiceRequest(3, "third")
				)
			), ChoiceFormQuestion.class),
			of(getShortQuestionRequest(2, "hello-world1"), ShortFormQuestion.class)
		);
	}

}

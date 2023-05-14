package me.nalab.survey.application.port.in.create;

import static org.junit.jupiter.api.Assertions.*;

import java.security.SecureRandom;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import me.nalab.core.idgenerator.idcore.IdGenerator;
import me.nalab.survey.domain.survey.ChoiceFormQuestion;
import me.nalab.survey.domain.survey.FormQuestionable;
import me.nalab.survey.domain.survey.ShortFormQuestion;

class SurveyCreateRequestTest {

	private static final IdGenerator ID_GENERATOR = () -> {
		SecureRandom secureRandom = new SecureRandom();
		return secureRandom.nextLong();
	};

	@ParameterizedTest
	@MethodSource("createSurveyRequestStream")
	void CREATE_SURVEY_SUCCESS(Supplier<SurveyCreateRequest> surveyCreateRequestSupplier){
		assertDoesNotThrow(surveyCreateRequestSupplier::get);
	}

	@ParameterizedTest
	@MethodSource("createConCreteStream")
	void CONVERT_TO_CONCRETE_CLASS_SUCCESS(Questionable<?> questionable, Class<? extends FormQuestionable> type){
		assertEquals(questionable.getConcreteQuestion(ID_GENERATOR).getClass(), type);
	}

	private static Stream<Arguments> createSurveyRequestStream(){
		return Stream.of(
			Arguments.of((Supplier<Object>)() -> getSurveyCreateRequest(0, List.of())),
			Arguments.of((Supplier<Object>)() -> getSurveyCreateRequest(0, null)),
			Arguments.of((Supplier<Object>)() -> getSurveyCreateRequest(
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
			Arguments.of((Supplier<Object>)() -> getSurveyCreateRequest(
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
			Arguments.of((Supplier<Object>)() -> getSurveyCreateRequest(
				2, List.of(
					getShortQuestionRequest(2, "hello-world1"),
					getShortQuestionRequest(3, "hello-world2")
				)
			))
		);
	}
	private static Stream<Arguments> createConCreteStream(){
		return Stream.of(
			Arguments.of(getChoiceQuestionRequest("hello-world1", 3, 1, List.of(
				getChoiceRequest(1, "first"),
				getChoiceRequest(2, "second"),
				getChoiceRequest(3, "third")
				)
			), ChoiceFormQuestion.class),
			Arguments.of(getShortQuestionRequest(2, "hello-world1"), ShortFormQuestion.class)
		);
	}

	private static SurveyCreateRequest getSurveyCreateRequest(Integer questionCount, List<Questionable<?>> questionableList){
		return SurveyCreateRequest.builder()
			.questionCount(questionCount)
			.questionableList(questionableList)
			.build();
	}

	private static Questionable<?> getChoiceQuestionRequest(String title, Integer maxSelectableCount,
		Integer order, List<ChoiceRequest> choiceRequestList){
		return ChoiceQuestionRequest.builder()
			.title(title)
			.maxSelectableCount(maxSelectableCount)
			.order(order)
			.choiceRequestList(choiceRequestList)
			.build();
	}

	private static ChoiceRequest getChoiceRequest(Integer order, String content){
		return ChoiceRequest.builder()
			.order(order)
			.content(content)
			.build();
	}

	private static Questionable<?> getShortQuestionRequest(Integer order, String title){
		return ShortQuestionRequest.builder()
			.title(title)
			.order(order)
			.build();
	}

}

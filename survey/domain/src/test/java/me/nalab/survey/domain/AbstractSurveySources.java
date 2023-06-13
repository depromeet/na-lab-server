package me.nalab.survey.domain;

import static org.junit.jupiter.params.provider.Arguments.of;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import me.nalab.survey.domain.survey.Choice;
import me.nalab.survey.domain.survey.ChoiceFormQuestion;
import me.nalab.survey.domain.survey.ChoiceFormQuestionType;
import me.nalab.survey.domain.survey.FormQuestionable;
import me.nalab.survey.domain.survey.QuestionType;
import me.nalab.survey.domain.survey.ShortFormQuestion;
import me.nalab.survey.domain.survey.ShortFormQuestionType;
import me.nalab.survey.domain.survey.Survey;

public abstract class AbstractSurveySources {

	protected static Stream<Arguments> surveyCreateSuccessSources() {
		return Stream.of(
			of(
				surveyFunction(1L, LocalDateTime.now(), LocalDateTime.now()),
				(Supplier<List<FormQuestionable>>)() -> {
					List<FormQuestionable> formQuestionables = new ArrayList<>();
					List<Choice> choiceList = new ArrayList<>();
					choiceList.add(choice(3L, 1, "choice1"));
					choiceList.add(choice(4L, 2, "choice2"));
					formQuestionables.add(choiceFormQuestion(2L, "choice-form1", LocalDateTime.now(),
						LocalDateTime.now(), 1, choiceList));
					formQuestionables.add(shortFormQuestion(5L, "short-form2", LocalDateTime.now(),
						LocalDateTime.now(), 2));
					return formQuestionables;
				}
			)
		);
	}

	protected static Stream<Arguments> surveyCreateFailOrderSources() {
		return Stream.of(
			of(
				surveyFunction(1L, LocalDateTime.now(), LocalDateTime.now()),
				(Supplier<List<FormQuestionable>>)() -> {
					List<FormQuestionable> formQuestionables = new ArrayList<>();
					List<Choice> choiceList = new ArrayList<>();
					choiceList.add(choice(3L, 1, "choice1"));
					choiceList.add(choice(4L, 2, "choice2"));
					formQuestionables.add(choiceFormQuestion(2L, "choice-form-fail1", LocalDateTime.now(),
						LocalDateTime.now(), 1, choiceList));
					formQuestionables.add(shortFormQuestion(11L, "short-form-fail", LocalDateTime.now(),
						LocalDateTime.now(), 1));
					return formQuestionables;
				}
			),
			of(
				surveyFunction(6L, LocalDateTime.now(), LocalDateTime.now()),
				(Supplier<List<FormQuestionable>>)() -> {
					List<FormQuestionable> formQuestionables = new ArrayList<>();
					List<Choice> choiceList = new ArrayList<>();
					choiceList.add(choice(8L, 2, "choice3"));
					choiceList.add(choice(4L, 2, "choice4"));
					choiceList.add(choice(4L, 2, "choice4"));
					formQuestionables.add(choiceFormQuestion(7L, "choice-form-fail3", LocalDateTime.now(),
						LocalDateTime.now(), 1, choiceList));
					return formQuestionables;
				}
			)
		);
	}

	protected static Stream<Arguments> surveyWithIdFailSources() {
		return Stream.of(
			of(
				surveyFunction(null, LocalDateTime.now(), LocalDateTime.now()),
				(Supplier<List<FormQuestionable>>)() -> {
					List<FormQuestionable> formQuestionables = new ArrayList<>();
					List<Choice> choiceList = new ArrayList<>();
					choiceList.add(choice(null, 1, "choice1"));
					choiceList.add(choice(1L, 2, "choice2"));
					formQuestionables.add(choiceFormQuestion(null, "choice-form-fail1", LocalDateTime.now(),
						LocalDateTime.now(), 1, choiceList));
					return formQuestionables;
				}
			),
			of(
				surveyFunction(null, LocalDateTime.now(), LocalDateTime.now()),
				(Supplier<List<FormQuestionable>>)() -> {
					List<FormQuestionable> formQuestionables = new ArrayList<>();
					List<Choice> choiceList = new ArrayList<>();
					choiceList.add(choice(1L, 1, "choice3"));
					choiceList.add(choice(1L, 2, "choice4"));
					formQuestionables.add(choiceFormQuestion(null, "choice-form-fail1", LocalDateTime.now(),
						LocalDateTime.now(), 1, choiceList));
					return formQuestionables;
				}
			),
			of(
				surveyFunction(null, LocalDateTime.now(), LocalDateTime.now()),
				(Supplier<List<FormQuestionable>>)() -> {
					List<FormQuestionable> formQuestionables = new ArrayList<>();
					List<Choice> choiceList = new ArrayList<>();
					choiceList.add(choice(1L, 1, "choice3"));
					choiceList.add(choice(1L, 2, "choice4"));
					formQuestionables.add(choiceFormQuestion(1L, "choice-form-fail1", LocalDateTime.now(),
						LocalDateTime.now(), 1, choiceList));
					return formQuestionables;
				}
			),
			of(
				surveyFunction(1L, LocalDateTime.now(), LocalDateTime.now()),
				(Supplier<List<FormQuestionable>>)() -> {
					List<FormQuestionable> formQuestionables = new ArrayList<>();
					List<Choice> choiceList = new ArrayList<>();
					choiceList.add(choice(1L, 1, "choice3"));
					choiceList.add(choice(1L, 2, "choice4"));
					formQuestionables.add(choiceFormQuestion(1L, "choice-form-fail1", LocalDateTime.now(),
						LocalDateTime.now(), 1, choiceList));
					return formQuestionables;
				}
			)
		);
	}

	static Function<Supplier<List<FormQuestionable>>, Survey> surveyFunction(Long id, LocalDateTime createAt,
		LocalDateTime updatedAt) {
		return T -> Survey.builder()
			.id(id)
			.createdAt(createAt)
			.updatedAt(updatedAt)
			.formQuestionableList(T.get())
			.build();
	}

	static FormQuestionable choiceFormQuestion(Long id, String title, LocalDateTime createdAt, LocalDateTime updatedAt,
		Integer order, List<Choice> choiceList) {
		return ChoiceFormQuestion.builder()
			.id(id)
			.title(title)
			.createdAt(createdAt)
			.updatedAt(updatedAt)
			.order(order)
			.questionType(QuestionType.CHOICE)
			.choiceFormQuestionType(ChoiceFormQuestionType.CUSTOM)
			.maxSelectableCount(choiceList.size())
			.choiceList(choiceList)
			.build();
	}

	static FormQuestionable shortFormQuestion(Long id, String title, LocalDateTime createdAt, LocalDateTime updatedAt,
		Integer order) {
		return ShortFormQuestion.builder()
			.id(id)
			.title(title)
			.createdAt(createdAt)
			.updatedAt(updatedAt)
			.order(order)
			.questionType(QuestionType.SHORT)
			.shortFormQuestionType(ShortFormQuestionType.CUSTOM)
			.build();
	}

	static Choice choice(Long id, Integer order, String content) {
		return Choice.builder()
			.id(id)
			.order(order)
			.content(content)
			.build();
	}

}

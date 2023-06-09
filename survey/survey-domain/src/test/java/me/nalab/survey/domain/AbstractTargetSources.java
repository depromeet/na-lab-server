package me.nalab.survey.domain;

import static org.junit.jupiter.params.provider.Arguments.of;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import me.nalab.survey.domain.survey.Choice;
import me.nalab.survey.domain.survey.FormQuestionable;
import me.nalab.survey.domain.survey.Survey;
import me.nalab.survey.domain.target.Target;

public abstract class AbstractTargetSources extends AbstractSurveySources {

	protected static Stream<Arguments> targetCreateSuccessSources() {
		return Stream.of(
			of(
				targetFunction(10L, "mike", "designer"),
				surveyFunction(1L, Instant.now(), Instant.now()),
				(Supplier<List<FormQuestionable>>)() -> {
					List<Choice> choiceList = new ArrayList<>();
					choiceList.add(Choice.builder().id(2L).order(1).content("choice1").build());
					choiceList.add(Choice.builder().id(4L).order(2).content("choice2").build());
					ArrayList<FormQuestionable> formQuestionables = new ArrayList<>();
					formQuestionables.add(
						choiceFormQuestion(2L, "choice-form1", Instant.now(), Instant.now(), 1,
							choiceList)
					);
					formQuestionables.add(
						shortFormQuestion(5L, "short-form2", Instant.now(), Instant.now(), 2)
					);
					return formQuestionables;
				}
			)
		);
	}

	static Function<List<Survey>, Target> targetFunction(Long id,
		String nickname, String position) {
		return T -> Target.builder()
			.id(id)
			.nickname(nickname)
			.position(position)
			.surveyList(T)
			.build();
	}

}

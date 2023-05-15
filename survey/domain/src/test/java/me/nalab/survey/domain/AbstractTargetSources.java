package me.nalab.survey.domain;

import static org.junit.jupiter.params.provider.Arguments.of;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import me.nalab.survey.domain.survey.FormQuestionable;
import me.nalab.survey.domain.survey.Survey;
import me.nalab.survey.domain.target.Target;

public abstract class AbstractTargetSources extends AbstractSurveySources {

	protected static Stream<Arguments> targetCreateSuccessSources() {
		return Stream.of(
			of(
				targetFunction(10L, "mike"),
				surveyFunction(1L, LocalDateTime.now(), LocalDateTime.now())
				, (Supplier<List<FormQuestionable>>)() -> List.of(
					choiceFormQuestion(2L, "choice-form1", LocalDateTime.now(), LocalDateTime.now(), 1,
						List.of(choice(3L, 1, "choice1"), choice(4L, 2, "choice2")))
					, shortFormQuestion(5L, "short-form2", LocalDateTime.now(), LocalDateTime.now(), 2
					)
				)
			)
		);
	}

	static Function<List<Survey>, Target> targetFunction(Long id,
		String nickname) {
		return T -> Target.builder()
			.id(id)
			.nickname(nickname)
			.surveyList(T)
			.build();
	}

}

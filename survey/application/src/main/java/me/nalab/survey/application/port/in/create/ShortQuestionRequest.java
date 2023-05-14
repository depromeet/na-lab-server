package me.nalab.survey.application.port.in.create;

import java.time.LocalDateTime;

import lombok.Builder;
import me.nalab.core.idgenerator.idcore.IdGenerator;
import me.nalab.survey.domain.survey.QuestionType;
import me.nalab.survey.domain.survey.ShortFormQuestion;
import me.nalab.survey.domain.survey.ShortFormQuestionType;

@Builder
public class ShortQuestionRequest implements Questionable<ShortFormQuestion>{

	private final String title;
	private final Integer order;

	@Override
	public ShortFormQuestion getConcreteQuestion(IdGenerator idGenerator) {
		return ShortFormQuestion.builder()
			.id(idGenerator.generate())
			.order(order)
			.questionType(QuestionType.SHORT)
			.shortFormQuestionType(ShortFormQuestionType.CUSTOM)
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.title(title)
			.build();
	}
}

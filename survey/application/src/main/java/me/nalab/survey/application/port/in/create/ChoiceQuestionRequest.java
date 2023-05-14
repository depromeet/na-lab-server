package me.nalab.survey.application.port.in.create;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Builder;
import me.nalab.core.idgenerator.idcore.IdGenerator;
import me.nalab.survey.domain.survey.Choice;
import me.nalab.survey.domain.survey.ChoiceFormQuestion;
import me.nalab.survey.domain.survey.ChoiceFormQuestionType;
import me.nalab.survey.domain.survey.QuestionType;

@Builder
public class ChoiceQuestionRequest implements Questionable<ChoiceFormQuestion>{

	private final String type;
	private final String title;
	private final List<ChoiceRequest> choiceRequestList;
	private final Integer maxSelectableCount;
	private final Integer order;

	@Override
	public ChoiceFormQuestion getConcreteQuestion(IdGenerator idGenerator) {
		return ChoiceFormQuestion.builder()
			.id(idGenerator.generate())
			.questionType(QuestionType.SHORT)
			.choiceFormQuestionType(ChoiceFormQuestionType.CUSTOM)
			.choiceList(
				choiceRequestList.stream()
					.map(cr -> Choice.builder()
						.id(idGenerator.generate())
						.content(cr.getContent())
						.order(cr.getOrder())
						.build())
					.collect(Collectors.toList())
			)
			.maxSelectionCount(maxSelectableCount)
			.order(order)
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.build();
	}

}

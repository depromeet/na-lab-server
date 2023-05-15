package me.nalab.survey.domain.survey;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import me.nalab.core.idgenerator.idcore.IdGenerator;
import me.nalab.survey.domain.survey.valid.ChoiceFormQuestionValidator;

@SuperBuilder
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ChoiceFormQuestion extends FormQuestionable {

	private final List<Choice> choiceList;
	private final Integer maxSelectionCount;
	private final ChoiceFormQuestionType choiceFormQuestionType;

	ChoiceFormQuestion(ChoiceFormQuestionBuilder<?, ?> builder) {
		super(builder);
		choiceList = builder.choiceList;
		maxSelectionCount = builder.maxSelectionCount;
		choiceFormQuestionType = builder.choiceFormQuestionType;
		ChoiceFormQuestionValidator.validSelf(this);
	}

	static List<ChoiceFormQuestion> getDefaultChoiceFormQuestion(IdGenerator idGenerator) {
		return Stream.of(ChoiceFormQuestionType.values())
			.filter(cf -> cf != ChoiceFormQuestionType.CUSTOM)
			.map(cf -> cf.getChoiceFormQuestion(idGenerator))
			.collect(Collectors.toList());
	}

	@Override
	FormQuestionable ofIncreaseOrder(int defaultOrderSize) {
		return builder()
			.id(id)
			.title(title)
			.createdAt(createdAt)
			.updatedAt(updatedAt)
			.order(defaultOrderSize + order)
			.questionType(questionType)
			.choiceList(choiceList)
			.maxSelectionCount(maxSelectionCount)
			.choiceFormQuestionType(choiceFormQuestionType)
			.build();
	}

}

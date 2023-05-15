package me.nalab.survey.domain.survey;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import me.nalab.core.idgenerator.idcore.IdGenerator;

@SuperBuilder
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ShortFormQuestion extends FormQuestionable {

	private final ShortFormQuestionType shortFormQuestionType;

	static List<ShortFormQuestion> getDefaultShortFormQuestion(IdGenerator idGenerator) {
		return Stream.of(ShortFormQuestionType.values())
			.filter(sf -> sf != ShortFormQuestionType.CUSTOM)
			.map(sf -> sf.getShortFormQuestion(idGenerator))
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
			.shortFormQuestionType(shortFormQuestionType)
			.build();
	}

}

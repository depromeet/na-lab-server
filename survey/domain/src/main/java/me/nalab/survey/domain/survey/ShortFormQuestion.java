package me.nalab.survey.domain.survey;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@EqualsAndHashCode(callSuper = true)
public class ShortFormQuestion extends FormQuestionable {

	private final ShortFormQuestionType shortFormQuestionType;

	@Override
	public String toString() {
		return "ShortFormQuestion{" +
			"shortFormQuestionType=" + shortFormQuestionType +
			", id=" + id +
			", title='" + title + '\'' +
			", createdAt=" + createdAt +
			", updatedAt=" + updatedAt +
			", order=" + order +
			", questionType=" + questionType +
			'}';
	}

}

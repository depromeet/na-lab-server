package me.nalab.survey.domain.survey;

import java.util.Objects;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
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

	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof FormQuestionable))
			return false;
		ShortFormQuestion that = (ShortFormQuestion)o;
		return shortFormQuestionType == that.shortFormQuestionType && id.equals(that.id) && title.equals(that.title)
			&& createdAt.equals(that.createdAt) && updatedAt.equals(
			that.updatedAt) && order.equals(that.order) && questionType == that.questionType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(shortFormQuestionType, id, title, createdAt, updatedAt, order, questionType);
	}

}

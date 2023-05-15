package me.nalab.survey.domain.survey;

import java.util.List;
import java.util.Objects;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import me.nalab.core.meta.coverage.Generated;

@SuperBuilder
@Getter
public class ChoiceFormQuestion extends FormQuestionable {

	private final List<Choice> choiceList;
	private final Integer maxSelectionCount;
	private final ChoiceFormQuestionType choiceFormQuestionType;

	@Override
	public String toString() {
		return "ChoiceFormQuestion{" +
			"choiceList=" + choiceList +
			", maxSelectionCount=" + maxSelectionCount +
			", choiceFormQuestionType=" + choiceFormQuestionType +
			", id=" + id +
			", title='" + title + '\'' +
			", createdAt=" + createdAt +
			", updatedAt=" + updatedAt +
			", order=" + order +
			", questionType=" + questionType +
			'}';
	}

	@Override
	@Generated
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof FormQuestionable))
			return false;
		ChoiceFormQuestion that = (ChoiceFormQuestion)o;
		return choiceList.equals(that.choiceList) && maxSelectionCount.equals(that.maxSelectionCount)
			&& choiceFormQuestionType == that.choiceFormQuestionType && id.equals(that.id) && title.equals(that.title)
			&& createdAt.equals(that.createdAt) && updatedAt.equals(
			that.updatedAt) && order.equals(that.order) && questionType == that.questionType;
	}

	@Override
	@Generated
	public int hashCode() {
		return Objects.hash(choiceList, maxSelectionCount, choiceFormQuestionType, id, title, createdAt, updatedAt,
			order, questionType);
	}

}

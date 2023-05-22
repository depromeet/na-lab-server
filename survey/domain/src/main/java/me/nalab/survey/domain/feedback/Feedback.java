package me.nalab.survey.domain.feedback;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.LongSupplier;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.nalab.survey.domain.exception.IdAlreadyGeneratedException;
import me.nalab.survey.domain.support.IdGeneratable;
import me.nalab.survey.domain.survey.Survey;
import me.nalab.survey.domain.survey.spi.FeedbackValidable;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class Feedback implements IdGeneratable, FeedbackValidable<FormQuestionFeedbackable> {

	private Long id;
	private Long surveyId;
	private final List<FormQuestionFeedbackable> formQuestionFeedbackableList;
	private final boolean isRead;
	private final Reviewer reviewer;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;

	@Override
	public void withId(LongSupplier idSupplier) {
		if(this.id != null) {
			throw new IdAlreadyGeneratedException(this);
		}
		id = idSupplier.getAsLong();
		formQuestionFeedbackableList.forEach(f -> f.withId(idSupplier));
	}

	@Override
	public boolean isValidFeedback(Survey survey) {
		return survey.getId().equals(surveyId);
	}

	@Override
	public List<FormQuestionFeedbackable> getAllQuestionFeedbackValidable() {
		return formQuestionFeedbackableList;
	}

}

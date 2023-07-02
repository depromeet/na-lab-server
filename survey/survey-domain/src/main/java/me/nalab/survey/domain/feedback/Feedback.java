package me.nalab.survey.domain.feedback;

import java.time.Instant;
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
public class Feedback implements IdGeneratable, FeedbackValidable<FormQuestionFeedbackable>, Comparable<Feedback> {

	private Long id;
	private Long surveyId;
	private final List<FormQuestionFeedbackable> formQuestionFeedbackableList;
	private boolean isRead;
	private final Reviewer reviewer;
	private final Instant createdAt;
	private final Instant updatedAt;

	public void setRead(boolean read) {
		isRead = read;
	}

	@Override
	public void withId(LongSupplier idSupplier) {
		if(this.id != null) {
			throw new IdAlreadyGeneratedException(this);
		}
		id = idSupplier.getAsLong();
		formQuestionFeedbackableList.forEach(f -> f.withId(idSupplier));
		if(reviewer != null) {
			reviewer.withId(idSupplier);
		}
	}

	@Override
	public boolean isValidFeedback(Survey survey) {
		return survey.getId().equals(surveyId);
	}

	@Override
	public List<FormQuestionFeedbackable> getAllQuestionFeedbackValidable() {
		return formQuestionFeedbackableList;
	}

	@Override
	public int compareTo(Feedback feedback) {
		if(updatedAt.isAfter(feedback.getUpdatedAt())) {
			return -1;
		}
		if(updatedAt.isBefore(feedback.getUpdatedAt())) {
			return 1;
		}
		if(createdAt.isAfter(feedback.getCreatedAt())) {
			return -1;
		}
		if(createdAt.isBefore(feedback.getCreatedAt())) {
			return 1;
		}
		return 0;
	}

}

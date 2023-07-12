package me.nalab.survey.domain.feedback;

import java.util.function.LongSupplier;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import me.nalab.survey.domain.exception.IdAlreadyGeneratedException;
import me.nalab.survey.domain.support.IdGeneratable;
import me.nalab.survey.domain.survey.spi.QuestionFeedbackValidable;

@SuperBuilder
@Getter
@ToString
@EqualsAndHashCode(exclude = "bookmark")
public abstract class FormQuestionFeedbackable implements IdGeneratable, QuestionFeedbackValidable {

	private Long id;
	private Long questionId;
	@Setter
	private boolean isRead;
	private Bookmark bookmark;

	public void replaceBookmark() {
		bookmark.replaceIsBookmarked();
	}

	@Override
	public void withId(LongSupplier idSupplier) {
		if (this.id != null) {
			throw new IdAlreadyGeneratedException(this);
		}
		id = idSupplier.getAsLong();
	}

	@Override
	public Long getFormQuestionId() {
		return questionId;
	}

	public boolean isBookmarked() {
		return bookmark.isBookmarked();
	}
}

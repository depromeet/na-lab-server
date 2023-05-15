package me.nalab.survey.domain.survey;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Survey {

	private final Long id;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;
	private final List<FormQuestionable> formQuestionableList;

	@Override
	public String toString() {
		return "Survey{" +
			"id=" + id +
			", createdAt=" + createdAt +
			", updatedAt=" + updatedAt +
			", formQuestionableList=" + formQuestionableList +
			'}';
	}
}

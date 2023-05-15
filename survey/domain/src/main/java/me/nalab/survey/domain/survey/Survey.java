package me.nalab.survey.domain.survey;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import lombok.Builder;
import lombok.Getter;
import me.nalab.core.meta.coverage.Generated;

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

	@Override
	@Generated
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof Survey))
			return false;
		Survey survey = (Survey)o;
		return id.equals(survey.id) && createdAt.equals(survey.createdAt) && updatedAt.equals(survey.updatedAt)
			&& formQuestionableList.equals(survey.formQuestionableList);
	}

	@Override
	@Generated
	public int hashCode() {
		return Objects.hash(id, createdAt, updatedAt, formQuestionableList);
	}

}

package me.nalab.survey.domain.survey;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.nalab.survey.domain.survey.valid.SurveyValidator;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class Survey {

	private final Long id;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;
	private final List<FormQuestionable> formQuestionableList;

	Survey(Long id, LocalDateTime createdAt, LocalDateTime updatedAt,
		List<FormQuestionable> formQuestionableList) {
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.formQuestionableList = formQuestionableList;
		SurveyValidator.validSelf(this);
	}

}
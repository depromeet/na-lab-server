package me.nalab.survey.web.adaptor.findspecific.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public abstract class FormFeedbackResponseable {

	@JsonProperty("question_id")
	private final long questionId;

	private final String type;
	@JsonProperty("form_type")
	private final String formType;

	private final String title;

	private final int order;

	@JsonProperty("is_read")
	private final boolean isRead;
}

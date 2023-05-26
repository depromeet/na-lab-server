package me.nalab.survey.web.adaptor.findspecific.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@ToString
@EqualsAndHashCode
public class FormFeedbackResponseable {

	@JsonProperty("question_id")
	private Long questionId;

	private String type;

	private String title;

	private Integer order;

	@JsonProperty("is_read")
	private Boolean isRead;
}

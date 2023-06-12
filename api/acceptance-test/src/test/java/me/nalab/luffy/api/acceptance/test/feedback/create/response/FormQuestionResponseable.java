package me.nalab.luffy.api.acceptance.test.feedback.create.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@ToString
@EqualsAndHashCode
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
	@JsonSubTypes.Type(value = ShortFormQuestionResponse.class, name = "short"),
	@JsonSubTypes.Type(value = ChoiceFormQuestionResponse.class, name = "choice"),
})
@NoArgsConstructor
public abstract class FormQuestionResponseable {

	@JsonProperty("question_id")
	private String questionId;

	@JsonProperty("form_type")
	private String formType;

	private String title;

	private Integer order;

}

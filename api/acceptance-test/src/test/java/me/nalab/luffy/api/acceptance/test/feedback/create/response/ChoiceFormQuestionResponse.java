package me.nalab.luffy.api.acceptance.test.feedback.create.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ChoiceFormQuestionResponse extends FormQuestionResponseable {

	@JsonProperty("max_selection_count")
	private Integer maxSelectionCount;

	private List<ChoiceResponse> choices;
}

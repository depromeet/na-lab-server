package me.nalab.survey.web.adaptor.findfeedback.formtype.response.formquestion;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ChoiceResponse {

	@JsonProperty("choice_id")
	private final String choiceId;

	private final int order;

	@JsonProperty("selected_count")
	private int selectedCount;

	private final String content;

	public void updateSelectedCount() {
		this.selectedCount++;
	}

}

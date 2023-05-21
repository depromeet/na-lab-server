package me.nalab.luffy.api.acceptance.test.survey.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ChoiceRequest {

	private String content;
	private Integer order;

}

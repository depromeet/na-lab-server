package me.nalab.survey.domain.survey;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Choice {

	private final Long id;
	private final String content;
	private final Integer order;

}

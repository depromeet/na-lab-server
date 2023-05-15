package me.nalab.survey.domain.survey;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class Choice {

	private final Long id;
	private final String content;
	private final Integer order;

}

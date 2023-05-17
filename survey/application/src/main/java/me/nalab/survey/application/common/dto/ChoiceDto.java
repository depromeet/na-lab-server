package me.nalab.survey.application.common.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class ChoiceDto {

	private final Long id;
	private final String content;
	private final Integer order;

}

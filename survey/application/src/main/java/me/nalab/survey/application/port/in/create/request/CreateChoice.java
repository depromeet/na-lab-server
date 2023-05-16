package me.nalab.survey.application.port.in.create.request;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class CreateChoice {

	private final String content;
	private final Integer order;

}

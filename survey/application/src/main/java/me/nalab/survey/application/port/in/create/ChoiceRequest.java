package me.nalab.survey.application.port.in.create;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter(AccessLevel.PACKAGE)
public class ChoiceRequest {

	private final String content;
	private final Integer order;

}

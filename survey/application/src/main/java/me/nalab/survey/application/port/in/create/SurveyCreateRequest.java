package me.nalab.survey.application.port.in.create;

import java.util.List;

import lombok.Builder;

@Builder
public class SurveyCreateRequest {

	private final Integer questionCount;
	private final List<Questionable<?>> questionableList;

}

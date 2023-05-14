package me.nalab.survey.application.port.in.create;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SurveyCreateRequest {

	private final Integer questionCount;
	private final List<Questionable<?>> questionableList;

}

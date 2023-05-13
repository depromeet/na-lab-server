package me.nalab.api.acceptance.survey.request.createsurvey;

import java.util.List;

import lombok.Builder;

@Builder
public class SurveyCreateRequest {

	private int questionCount;
	private List<Questionable> question;

}
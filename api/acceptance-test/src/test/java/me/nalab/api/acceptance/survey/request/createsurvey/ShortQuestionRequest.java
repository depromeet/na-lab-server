package me.nalab.api.acceptance.survey.request.createsurvey;

import lombok.Builder;

@Builder
public class ShortQuestionRequest implements Questionable{

	private String type;
	private String title;
	private int order;

}

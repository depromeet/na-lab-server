package me.nalab.api.acceptance.survey.request.createsurvey;

import java.util.List;

import lombok.Builder;

@Builder
public class ChoiceQuestionRequest implements Questionable{

	private String type;
	private String title;
	private int order;
	private List<Choice> choices;

	@Builder
	public static class Choice{

		private String content;
		private int order;

	}

}

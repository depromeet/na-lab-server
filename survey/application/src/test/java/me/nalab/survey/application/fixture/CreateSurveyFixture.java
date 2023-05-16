package me.nalab.survey.application.fixture;

import java.util.List;

import me.nalab.survey.application.port.in.create.request.CreateChoice;
import me.nalab.survey.application.port.in.create.request.CreateChoiceQuestion;
import me.nalab.survey.application.port.in.create.request.CreateQuestionable;
import me.nalab.survey.application.port.in.create.request.CreateShortQuestion;
import me.nalab.survey.application.port.in.create.request.CreateSurveyRequest;
import me.nalab.survey.domain.survey.FormQuestionable;
import me.nalab.survey.domain.survey.ShortFormQuestion;

public class CreateSurveyFixture {

	public static CreateSurveyRequest getSurveyCreateRequest(Integer questionCount
		, List<CreateQuestionable<? extends FormQuestionable>> questionableList) {
		return CreateSurveyRequest.builder()
			.questionCount(questionCount)
			.questionableList(questionableList)
			.build();
	}

	public static CreateQuestionable<? extends FormQuestionable> getChoiceQuestionRequest(String title
		, Integer maxSelectableCount
		, Integer order
		, List<CreateChoice> choiceRequestList) {
		return CreateChoiceQuestion.builder()
			.title(title)
			.maxSelectableCount(maxSelectableCount)
			.order(order)
			.choiceRequestList(choiceRequestList)
			.build();
	}

	public static CreateChoice getChoiceRequest(Integer order, String content) {
		return CreateChoice.builder()
			.order(order)
			.content(content)
			.build();
	}

	public static CreateQuestionable<ShortFormQuestion> getShortQuestionRequest(Integer order, String title) {
		return CreateShortQuestion.builder()
			.title(title)
			.order(order)
			.build();
	}

}

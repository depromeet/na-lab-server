package me.nalab.survey.application.port.fixture;

import java.util.List;

import me.nalab.survey.application.port.in.create.ChoiceQuestionRequest;
import me.nalab.survey.application.port.in.create.ChoiceRequest;
import me.nalab.survey.application.port.in.create.Questionable;
import me.nalab.survey.application.port.in.create.ShortQuestionRequest;
import me.nalab.survey.application.port.in.create.SurveyCreateRequest;

public class CreateSurveyFixture {

	public static SurveyCreateRequest getSurveyCreateRequest(Integer questionCount, List<Questionable<?>> questionableList){
		return SurveyCreateRequest.builder()
			.questionCount(questionCount)
			.questionableList(questionableList)
			.build();
	}

	public static Questionable<?> getChoiceQuestionRequest(String title, Integer maxSelectableCount,
		Integer order, List<ChoiceRequest> choiceRequestList){
		return ChoiceQuestionRequest.builder()
			.title(title)
			.maxSelectableCount(maxSelectableCount)
			.order(order)
			.choiceRequestList(choiceRequestList)
			.build();
	}

	public static ChoiceRequest getChoiceRequest(Integer order, String content){
		return ChoiceRequest.builder()
			.order(order)
			.content(content)
			.build();
	}

	public static Questionable<?> getShortQuestionRequest(Integer order, String title){
		return ShortQuestionRequest.builder()
			.title(title)
			.order(order)
			.build();
	}

}

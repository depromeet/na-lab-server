package me.nalab.survey.application.port.in.create.request;

import java.util.List;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.nalab.survey.domain.survey.FormQuestionable;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class CreateSurveyRequest {

	private final Integer questionCount;
	private final List<CreateQuestionable<? extends FormQuestionable>> questionableList;

}

package me.nalab.survey.domain.target;

import java.util.List;

import lombok.Builder;
import me.nalab.survey.domain.survey.Survey;

@Builder
public class Target{

	private final Long id;
	private final List<Survey> surveyList;
	private final String nickname;

}

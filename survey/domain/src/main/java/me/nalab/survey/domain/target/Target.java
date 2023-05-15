package me.nalab.survey.domain.target;

import java.util.List;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.nalab.survey.domain.survey.Survey;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class Target {

	private final Long id;
	private final List<Survey> surveyList;
	private final String nickname;

}

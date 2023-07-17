package me.nalab.survey.web.adaptor.existsurvey.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class SurveyExistResponse {

	private final boolean exists;

}

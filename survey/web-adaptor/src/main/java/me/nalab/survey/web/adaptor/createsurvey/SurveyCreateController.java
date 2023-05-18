package me.nalab.survey.web.adaptor.createsurvey;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.port.in.web.CreateSurveyUseCase;
import me.nalab.survey.application.port.in.web.LatestSurveyIdFindUseCase;
import me.nalab.survey.web.adaptor.createsurvey.request.SurveyCreateRequest;
import me.nalab.survey.web.adaptor.createsurvey.response.SurveyIdResponse;

@RestController
@RequiredArgsConstructor
class SurveyCreateController {

	private final CreateSurveyUseCase createSurveyUseCase;
	private final LatestSurveyIdFindUseCase latestSurveyIdFindUseCase;

	@PostMapping("/v1/surveys")
	@ResponseStatus(HttpStatus.CREATED)
	SurveyIdResponse createSurvey(@RequestAttribute("logined") Long loginId, SurveyCreateRequest surveyCreateRequest) {
		createSurveyUseCase.createSurvey(loginId, SurveyCreateRequestMapper.toSurveyDto(surveyCreateRequest));
		return SurveyIdResponse.builder()
			.surveyId(latestSurveyIdFindUseCase.getLatestCreatedSurveyId(loginId))
			.build();
	}

}

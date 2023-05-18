package me.nalab.survey.web.adaptor.createsurvey;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.port.in.web.CreateSurveyUseCase;
import me.nalab.survey.application.port.in.web.LatestSurveyIdFindUseCase;
import me.nalab.survey.web.adaptor.createsurvey.request.SurveyCreateRequest;
import me.nalab.survey.web.adaptor.createsurvey.response.SurveyIdResponse;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
class SurveyCreateController {

	private final CreateSurveyUseCase createSurveyUseCase;
	private final LatestSurveyIdFindUseCase latestSurveyIdFindUseCase;

	@PostMapping("/surveys")
	@ResponseStatus(HttpStatus.CREATED)
	SurveyIdResponse createSurvey(@RequestAttribute("logined") Long loginId, @RequestBody SurveyCreateRequest surveyCreateRequest) {
		createSurveyUseCase.createSurvey(loginId, SurveyCreateRequestMapper.toSurveyDto(surveyCreateRequest));
		Long latestSurveyId = latestSurveyIdFindUseCase.getLatestSurveyIdByTaregetId(loginId);
		return new SurveyIdResponse(latestSurveyId);
	}

}

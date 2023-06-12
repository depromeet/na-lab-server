package me.nalab.survey.web.adaptor.findid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.port.in.web.getid.SurveyIdGetUseCase;
import me.nalab.survey.web.adaptor.findid.response.SurveyIdResponse;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class SurveyIdGetController {

	private final SurveyIdGetUseCase surveyIdGetUseCase;

	@GetMapping("/surveys-id")
	@ResponseStatus(HttpStatus.OK)
	SurveyIdResponse getSurveyId(@RequestAttribute("logined") Long loginId) {
		Long surveyId = surveyIdGetUseCase.getSurveyIdByTargetId(loginId);
		return new SurveyIdResponse(surveyId);
	}

}

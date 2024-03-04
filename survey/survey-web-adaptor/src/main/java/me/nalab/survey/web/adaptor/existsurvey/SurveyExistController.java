package me.nalab.survey.web.adaptor.existsurvey;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.port.in.web.existsurvey.SurveyExistUseCase;
import me.nalab.survey.web.adaptor.existsurvey.response.SurveyExistResponse;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class SurveyExistController {

	private final SurveyExistUseCase surveyExistUseCase;

	@GetMapping("/surveys/exists")
	@ResponseStatus(HttpStatus.OK)
	public SurveyExistResponse existSurveyByTargetId(@RequestAttribute("logined") Long loginId) {
		boolean isSurveyExists = surveyExistUseCase.existSurveyByTargetId(loginId);
		return new SurveyExistResponse(isSurveyExists);
	}

}

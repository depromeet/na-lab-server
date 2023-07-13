package me.nalab.survey.web.adaptor.findtarget;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.common.survey.dto.TargetDto;
import me.nalab.survey.application.port.in.web.findtarget.TargetFindBySurveyIdUseCase;
import me.nalab.survey.web.adaptor.findtarget.response.TargetResponse;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class TargetFindBySurveyIdController {

	private final TargetFindBySurveyIdUseCase targetFindBySurveyIdUseCase;

	@GetMapping("/users")
	@ResponseStatus(HttpStatus.OK)
	public TargetResponse findTargetBySurveyId(@RequestParam("survey-id") Long surveyId) {
		TargetDto targetDto = targetFindBySurveyIdUseCase.findTargetBySurveyId(surveyId);
		return TargetResponseMapper.toTargetResponse(targetDto);
	}

}

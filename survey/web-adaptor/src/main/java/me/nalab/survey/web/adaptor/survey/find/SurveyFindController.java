package me.nalab.survey.web.adaptor.survey.find;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.common.dto.SurveyDto;
import me.nalab.survey.application.common.dto.TargetDto;
import me.nalab.survey.application.port.in.web.SurveyFindUseCase;
import me.nalab.survey.application.port.in.web.TargetFindUseCase;
import me.nalab.survey.web.adaptor.survey.find.mapper.SurveyFindResponseMapper;
import me.nalab.survey.web.adaptor.survey.find.response.SurveyFindResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SurveyFindController {

	private final TargetFindUseCase targetFindUseCase;
	private final SurveyFindUseCase surveyFindUseCase;
	private final SurveyFindResponseMapper surveyFindResponseMapper;

	@GetMapping("/v1/surveys/{survey_id}")
	public ResponseEntity<SurveyFindResponse> getSurvey(@PathVariable Long survey_id) {
		TargetDto targetDto = targetFindUseCase.findTarget(survey_id);
		SurveyDto surveyDto = surveyFindUseCase.findSurvey(survey_id);
		SurveyFindResponse surveyFindResponse = surveyFindResponseMapper.toSurveyFindResponse(targetDto, surveyDto);
		return ResponseEntity.ok(surveyFindResponse);
	}
}

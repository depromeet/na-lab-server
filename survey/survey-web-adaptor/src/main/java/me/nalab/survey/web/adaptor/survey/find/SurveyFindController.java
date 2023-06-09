package me.nalab.survey.web.adaptor.survey.find;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.common.survey.dto.SurveyDto;
import me.nalab.survey.application.common.survey.dto.TargetDto;
import me.nalab.survey.application.port.in.web.survey.find.SurveyFindUseCase;
import me.nalab.survey.application.port.in.web.target.find.TargetFindUseCase;
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

	@GetMapping("/v1/surveys/{surveyId}")
	public ResponseEntity<SurveyFindResponse> getSurvey(@PathVariable Long surveyId) {
		SurveyDto surveyDto = surveyFindUseCase.findSurvey(surveyId);
		TargetDto targetDto = targetFindUseCase.findTarget(surveyDto.getTargetId());
		SurveyFindResponse surveyFindResponse = SurveyFindResponseMapper.toSurveyFindResponse(targetDto, surveyDto);
		return ResponseEntity.ok(surveyFindResponse);
	}
}

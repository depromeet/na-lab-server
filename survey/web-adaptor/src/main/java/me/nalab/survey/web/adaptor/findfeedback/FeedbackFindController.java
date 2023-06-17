package me.nalab.survey.web.adaptor.findfeedback;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.nalab.core.authorization.aop.meta.Auth;
import me.nalab.core.authorization.aop.meta.Authorization;
import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.common.survey.dto.SurveyDto;
import me.nalab.survey.application.port.in.web.findfeedback.FeedbackFindUseCase;
import me.nalab.survey.application.port.in.web.survey.find.SurveyFindUseCase;
import me.nalab.survey.application.service.authorization.SurveyIdValidatorFactory;
import me.nalab.survey.web.adaptor.findfeedback.response.QuestionFeedbackResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class FeedbackFindController {

	private final SurveyFindUseCase surveyFindUseCase;
	private final FeedbackFindUseCase feedbackFindUseCase;

	@Authorization(factory = SurveyIdValidatorFactory.class)
	@GetMapping("/feedbacks")
	@ResponseStatus(HttpStatus.OK)
	public QuestionFeedbackResponse findFeedback(@Auth @RequestParam("survey-id") Long surveyId) {
		SurveyDto surveyDto = surveyFindUseCase.findSurvey(surveyId);
		List<FeedbackDto> feedbackDto = feedbackFindUseCase.findAllFeedbackDtoBySurveyId(surveyId);
		return ResponseMapper.toQuestionFeedbackResponse(surveyDto, feedbackDto);
	}

}

package me.nalab.survey.web.adaptor.findfeedback;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class FeedbackFindController {

	private final SurveyFindUseCase surveyFindUseCase;
	private final FeedbackFindUseCase feedbackFindUseCase;

	@Authorization(factory = SurveyIdValidatorFactory.class)
	@GetMapping("/v1/feedbacks")
	@ResponseStatus(HttpStatus.OK)
	public QuestionFeedbackResponse findFeedback(@Auth @RequestParam("survey-id") Long surveyId,
		@RequestParam(value = "form-type", required = false) String formType) {
		if(formType == null) {
			return findFeedback(String.valueOf(surveyId));
		}
		// TODO: form-type에 해당하는 feedback 조회 기능 구현
		throw new IllegalStateException("FormType method is not yet implemented form-type \"" + formType + "\"");
	}

	@Authorization(factory = SurveyIdValidatorFactory.class)
	@GetMapping("/v2/surveys/{survey-id}/feedbacks")
	public QuestionFeedbackResponse findFeedback(@Auth @PathVariable("survey-id") String surveyId) {
		Long convertedSurveyId = Long.valueOf(surveyId);
		SurveyDto surveyDto = surveyFindUseCase.findSurvey(convertedSurveyId);
		List<FeedbackDto> feedbackDto = feedbackFindUseCase.findAllFeedbackDtoBySurveyId(convertedSurveyId);
		feedbackFindUseCase.updateFormFeedbackEntityIsReadBySurveyId(convertedSurveyId);
		return ResponseMapper.toQuestionFeedbackResponse(surveyDto, feedbackDto);
	}

}

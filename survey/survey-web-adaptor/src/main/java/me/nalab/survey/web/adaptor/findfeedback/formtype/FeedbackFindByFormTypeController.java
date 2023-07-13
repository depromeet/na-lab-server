package me.nalab.survey.web.adaptor.findfeedback.formtype;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.common.survey.dto.FormQuestionDtoable;
import me.nalab.survey.application.port.in.web.findfeedback.formtype.FeedbackFindByTypeUseCase;
import me.nalab.survey.web.adaptor.findfeedback.formtype.response.QuestionFeedbackResponse;

@RestController
@RequestMapping("/v2")
@RequiredArgsConstructor
public class FeedbackFindByFormTypeController {

	private final FeedbackFindByTypeUseCase feedbackFindByTypeUseCase;

	@GetMapping("/feedbacks")
	@ResponseStatus(HttpStatus.OK)
	public QuestionFeedbackResponse findFeedbackByFormType(
		@RequestParam("survey-id") Long surveyId,
		@RequestParam("form-type") String formType) {
		List<FormQuestionDtoable> formQuestionDtoableList = feedbackFindByTypeUseCase.formQuestionMatchingWithType(
			surveyId, formType);
		List<FeedbackDto> feedbackDtoList = feedbackFindByTypeUseCase.findFeedbackBySurveyId(surveyId);
		return QuestionFeedbackResponseMapper.toFeedbackQuestionResponse(
			formQuestionDtoableList, feedbackDtoList);
	}

}

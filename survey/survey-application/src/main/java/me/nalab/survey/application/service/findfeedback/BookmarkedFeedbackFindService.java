package me.nalab.survey.application.service.findfeedback;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.common.feedback.mapper.FeedbackDtoMapper;
import me.nalab.survey.application.port.in.web.findfeedback.BookmarkedFeedbackFindUseCase;
import me.nalab.survey.application.port.out.persistence.findfeedback.FeedbackFindPort;
import me.nalab.survey.domain.feedback.Feedback;
import me.nalab.survey.domain.feedback.FormQuestionFeedbackable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookmarkedFeedbackFindService implements BookmarkedFeedbackFindUseCase {

	private final FeedbackFindPort feedbackFindPort;

	@Override
	@Transactional(readOnly = true)
	public List<FeedbackDto> findAllBySurveyId(Long surveyId) {
		List<Feedback> allFeedbacks = feedbackFindPort.findAllFeedbackBySurveyId(surveyId);

		return allFeedbacks.stream()
						   .filter(fb -> {
							   List<FormQuestionFeedbackable> allQuestionFeedbackValidable = fb.getAllQuestionFeedbackValidable();
							   return !allQuestionFeedbackValidable.isEmpty() && allQuestionFeedbackValidable.stream().anyMatch(FormQuestionFeedbackable::isBookmarked);
						   })
						   .map(FeedbackDtoMapper::toDtoWithBookmarkedForm)
						   .collect(Collectors.toList());
	}
}

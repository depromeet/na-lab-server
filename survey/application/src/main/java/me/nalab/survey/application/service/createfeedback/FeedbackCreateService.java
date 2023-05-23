package me.nalab.survey.application.service.createfeedback;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.nalab.core.idgenerator.idcore.IdGenerator;
import me.nalab.survey.application.common.feedback.dto.FeedbackDto;
import me.nalab.survey.application.common.feedback.mapper.FeedbackDtoMapper;
import me.nalab.survey.application.exception.SurveyDoesNotExistException;
import me.nalab.survey.application.port.in.web.createfeedback.FeedbackCreateUseCase;
import me.nalab.survey.application.port.out.persistence.createfeedback.FeedbackSavePort;
import me.nalab.survey.application.port.out.persistence.createfeedback.ReviewerLatestNameFindPort;
import me.nalab.survey.application.port.out.persistence.createfeedback.SurveyFindPort;
import me.nalab.survey.domain.feedback.Feedback;
import me.nalab.survey.domain.feedback.Reviewer;
import me.nalab.survey.domain.survey.Survey;

@Service
@RequiredArgsConstructor
public class FeedbackCreateService implements FeedbackCreateUseCase {

	private final SurveyFindPort surveyFindPort;
	private final FeedbackSavePort feedbackSavePort;
	private final IdGenerator idGenerator;
	private final ReviewerLatestNameFindPort reviewerLatestNameFindPort;
	private static final Object LOCK = new Object();

	@Override
	@Transactional
	public void createFeedback(Long surveyId, FeedbackDto feedbackDto) {
		Survey survey = surveyFindPort.findSurveyBySurveyId(surveyId).orElseThrow(
			() -> {
				throw new SurveyDoesNotExistException(surveyId);
			}
		);
		Feedback feedback = FeedbackDtoMapper.toDomain(survey, feedbackDto);
		feedback.withId(idGenerator::generate);
		survey.throwIfIsNotValidFeedback(feedback);
		generateNickName(surveyId, feedback.getReviewer());
		feedbackSavePort.saveFeedback(feedback);
	}

	private void generateNickName(Long surveyId, Reviewer reviewer){
		synchronized(LOCK) {
			String latestReviewerName = reviewerLatestNameFindPort.getLatestReviewerNameBySurveyId(surveyId)
					.orElse(null);
			if(latestReviewerName == null) {
				reviewer.generateFirstReviewerNickName();
				return;
			}
			reviewer.generateNickName(latestReviewerName);
		}
	}

}

package me.nalab.survey.application.service.summaryreviewer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.exception.SurveyDoesNotExistException;
import me.nalab.survey.application.port.in.web.summaryreviewer.ReviewerSummarizeUseCase;
import me.nalab.survey.application.port.out.persistence.summaryreviewer.ReviewerFindPort;
import me.nalab.survey.application.port.out.persistence.summaryreviewer.SurveyExistCheckPort;
import me.nalab.survey.domain.feedback.Reviewer;

@Service
@RequiredArgsConstructor
public class ReviewerSummaryGetService implements ReviewerSummarizeUseCase {

	private final ReviewerFindPort reviewerFindPort;
	private final SurveyExistCheckPort surveyExistCheckPort;

	@Override
	@Transactional(readOnly = true)
	public ReviewerSummaryDto summarizeReviewerBySurveyId(Long surveyId) {
		throwIfSurveyDoesNotExist(surveyId);
		List<Reviewer> reviewerList = reviewerFindPort.findAllReviewer(surveyId);
		int collaborationExperienceCount = getCollaborationExperienceCount(reviewerList);
		Map<String, Integer> positionMap = getPositionMap(reviewerList);
		return ReviewerSummaryDto.builder()
			.collaborationExperience(ReviewerSummaryDto.CollaborationExperience.builder()
				.yes(collaborationExperienceCount)
				.no(reviewerList.size() - collaborationExperienceCount).build())
			.position(ReviewerSummaryDto.Position.builder()
				.positionReplyMap(positionMap).build())
			.build();
	}

	private void throwIfSurveyDoesNotExist(Long surveyId) {
		if(surveyExistCheckPort.isExistSurveyBySurveyId(surveyId)) {
			return;
		}
		throw new SurveyDoesNotExistException(surveyId);
	}

	private int getCollaborationExperienceCount(List<Reviewer> reviewerList) {
		int collaborationExperienceCount = 0;
		for(Reviewer reviewer : reviewerList) {
			if(reviewer.isCollaborationExperience()) {
				collaborationExperienceCount++;
			}
		}
		return collaborationExperienceCount;
	}

	private Map<String, Integer> getPositionMap(List<Reviewer> reviewerList) {
		Map<String, Integer> positionMap = new HashMap<>();
		reviewerList.forEach(
			r -> {
				Integer count = positionMap.getOrDefault(r.getPosition(), 0);
				positionMap.put(r.getPosition(), count + 1);
			}
		);
		return positionMap;
	}

}

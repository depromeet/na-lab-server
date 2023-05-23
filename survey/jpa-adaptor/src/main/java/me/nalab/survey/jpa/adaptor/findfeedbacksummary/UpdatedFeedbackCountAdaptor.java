package me.nalab.survey.jpa.adaptor.findfeedbacksummary;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import me.nalab.core.data.feedback.FeedbackEntity;
import me.nalab.survey.application.port.out.persistence.feedbacksummary.UpdatedFeedbackCountPort;

@Repository
@RequiredArgsConstructor
public class UpdatedFeedbackCountAdaptor implements UpdatedFeedbackCountPort {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public int getUpdatedFeedbackCountBySurveyId(Long surveyId) {
		String queryString = "SELECT f FROM FeedbackEntity f WHERE f.surveyId = :surveyId AND f.isRead = :isRead";
		List<FeedbackEntity> resultList = entityManager.createQuery(queryString, FeedbackEntity.class)
			.setParameter("surveyId", surveyId)
			.setParameter("isRead", false)
			.getResultList();
		return resultList.size();
	}
}

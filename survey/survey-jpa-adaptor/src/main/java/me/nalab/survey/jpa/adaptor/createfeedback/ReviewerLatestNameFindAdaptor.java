package me.nalab.survey.jpa.adaptor.createfeedback;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import me.nalab.survey.application.port.out.persistence.createfeedback.ReviewerLatestNameFindPort;

@Repository
public class ReviewerLatestNameFindAdaptor implements ReviewerLatestNameFindPort {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Optional<String> getLatestReviewerNameBySurveyId(Long surveyId) {
		try {
			String latestName = entityManager.createQuery(
					"select f.reviewer.nickName from FeedbackEntity as f where f.surveyId = :surveyId order by f.reviewer.createdAt desc",
					String.class)
				.setParameter("surveyId", surveyId)
				.setMaxResults(1)
				.getSingleResult();
			return Optional.of(latestName);
		} catch(NoResultException noResultException) {
			return Optional.empty();
		}
	}

}

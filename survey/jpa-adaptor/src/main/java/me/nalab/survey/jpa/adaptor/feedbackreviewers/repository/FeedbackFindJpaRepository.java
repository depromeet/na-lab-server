package me.nalab.survey.jpa.adaptor.feedbackreviewers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.nalab.core.data.feedback.FeedbackEntity;

@Repository("feedbackreviewers.FeedbackFindJpaRepository")
public interface FeedbackFindJpaRepository extends JpaRepository<FeedbackEntity, Long> {

	List<FeedbackEntity> findBySurveyId(Long surveyId);
}

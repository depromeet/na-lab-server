package me.nalab.survey.jpa.adaptor.findfeedbacksummary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.nalab.core.data.feedback.FeedbackEntity;

public interface FeedbackCountJpaRepository extends JpaRepository<FeedbackEntity, Long> {

	long countBySurveyId(Long surveyId);

	long countBySurveyIdAndIsReadFalse(Long surveyId);

}

package me.nalab.survey.jpa.adaptor.findfeedbacksummary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import me.nalab.core.data.feedback.FeedbackEntity;

public interface TestTotalFeedbackCountRepository extends JpaRepository<FeedbackEntity, Long> {

	List<FeedbackEntity> findBySurveyId(Long surveyId);
}

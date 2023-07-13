package me.nalab.survey.jpa.adaptor.summaryreviewer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import me.nalab.core.data.feedback.FeedbackEntity;
import me.nalab.core.data.feedback.ReviewerEntity;

public interface ReviewerFindJpaRepository extends JpaRepository<FeedbackEntity, Long> {

	@Query("select f.reviewer from FeedbackEntity as f where f.surveyId = :surveyId")
	List<ReviewerEntity> findAllReviewerBySurveyId(@Param("surveyId") Long surveyId);

}

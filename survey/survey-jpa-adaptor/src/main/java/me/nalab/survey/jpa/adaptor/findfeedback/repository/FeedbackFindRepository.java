package me.nalab.survey.jpa.adaptor.findfeedback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import me.nalab.core.data.feedback.FeedbackEntity;

public interface FeedbackFindRepository extends JpaRepository<FeedbackEntity, Long> {

	@Query("select f from FeedbackEntity as f where f.surveyId = :surveyId")
	List<FeedbackEntity> findAllFeedbackEntityBySurveyId(@Param("surveyId") Long surveyId);

}

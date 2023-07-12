package me.nalab.survey.jpa.adaptor.authorization.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import me.nalab.core.data.target.TargetEntity;

public interface TargetIdFindJpaRepository extends JpaRepository<TargetEntity, Long> {

	@Query("select t.id from TargetEntity as t join SurveyEntity as s on s.id = :surveyId and s.targetId = t.id")
	Optional<Long> findTargetIdBySurveyId(@Param("surveyId") Long surveyId);

	@Query("select t.id from TargetEntity as t join SurveyEntity as s on s.id = (select f.surveyId from FeedbackEntity as f where f.id = :feedbackId) and s.targetId = t.id")
	Optional<Long> findTargetIdByFeedbackId(@Param("feedbackId") Long feedbackId);

	@Query("select t.id from TargetEntity as t join SurveyEntity as s on s.id = (select ff.feedbackEntity.surveyId from FormFeedbackEntity as ff where ff.id = :formQuestionFeedbackId) and s.targetId = t.id")
	Optional<Long> findTargetIdByFormQuestionFeedbackId(@Param("formQuestionFeedbackId") Long formQuestionFeedbackId);
}

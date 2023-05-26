package me.nalab.survey.jpa.adaptor.feedbackreviewers;

import org.springframework.data.jpa.repository.JpaRepository;

import me.nalab.core.data.survey.SurveyEntity;

public interface TestSurveyCreateJpaRepository extends JpaRepository<SurveyEntity, Long> {
}

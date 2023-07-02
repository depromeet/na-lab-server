package me.nalab.survey.jpa.adaptor.feedbackreviewers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.nalab.core.data.survey.SurveyEntity;

public interface TestSurveyJpaRepository extends JpaRepository<SurveyEntity, Long> {
}

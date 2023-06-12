package me.nalab.survey.jpa.adaptor.findfeedbacksummary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.nalab.core.data.survey.SurveyEntity;

public interface SurveyExistCheckJpaRepository extends JpaRepository<SurveyEntity, Long> {
}

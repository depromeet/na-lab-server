package me.nalab.survey.jpa.adaptor.create.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.nalab.core.data.survey.SurveyEntity;

public interface SurveyCreateJpaRepository extends JpaRepository<SurveyEntity, Long> {
}

package me.nalab.survey.jpa.adaptor.summaryreviewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.nalab.core.data.survey.SurveyEntity;

public interface SurveyExistCheckJpaRepository extends JpaRepository<SurveyEntity, Long> {
}

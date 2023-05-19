package me.nalab.survey.jpa.adaptor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.nalab.core.data.survey.SurveyEntity;

public interface SurveyRepository extends JpaRepository<SurveyEntity, Long> {
}

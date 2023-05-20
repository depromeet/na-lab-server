package me.nalab.survey.jpa.adaptor.find.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.nalab.core.data.survey.SurveyEntity;

public interface SurveyFindRepository extends JpaRepository<SurveyEntity, Long> {
}

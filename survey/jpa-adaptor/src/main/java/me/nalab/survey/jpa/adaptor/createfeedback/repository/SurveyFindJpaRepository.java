package me.nalab.survey.jpa.adaptor.createfeedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.nalab.core.data.survey.SurveyEntity;

@Repository("createfeedback.SurveyFindJpaRepository")
public interface SurveyFindJpaRepository extends JpaRepository<SurveyEntity, Long> {



}

package me.nalab.survey.jpa.adaptor.findfeedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.nalab.core.data.survey.SurveyEntity;

@Repository("findfeedback.SurveyExistCheckJpaRepository")
public interface SurveyExistCheckJpaRepository extends JpaRepository<SurveyEntity, Long> {
}

package me.nalab.survey.jpa.adaptor.findfeedback.formtype.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.nalab.core.data.survey.SurveyEntity;

@Repository("findfeedback.formtype.SurveyFindRepository")
public interface SurveyFindRepository extends JpaRepository<SurveyEntity, Long> {

}

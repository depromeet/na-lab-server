package me.nalab.survey.jpa.adaptor.existsurvey;

import org.springframework.data.jpa.repository.JpaRepository;

import me.nalab.core.data.survey.SurveyEntity;

public interface TestSurveyFindRepository extends JpaRepository<SurveyEntity, Long> {

	boolean existsByTargetId(Long targetId);
}

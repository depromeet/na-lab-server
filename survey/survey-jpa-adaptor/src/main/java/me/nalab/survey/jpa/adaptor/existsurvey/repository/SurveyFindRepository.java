package me.nalab.survey.jpa.adaptor.existsurvey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.nalab.core.data.survey.SurveyEntity;

@Repository("existsurvey.SurveyFindRepository")
public interface SurveyFindRepository extends JpaRepository<SurveyEntity, Long> {
	boolean existsByTargetId(Long targetId);
}

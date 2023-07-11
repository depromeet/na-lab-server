package me.nalab.survey.jpa.adaptor.findtarget;

import org.springframework.data.jpa.repository.JpaRepository;

import me.nalab.core.data.survey.SurveyEntity;

public interface TestTargetIdFindJpaRepository extends JpaRepository<SurveyEntity, Long> {

}

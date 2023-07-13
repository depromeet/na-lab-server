package me.nalab.survey.jpa.adaptor.findtarget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.nalab.core.data.survey.SurveyEntity;

@Repository("findtarget.TargetIdFindJpaRepository")
public interface TargetIdFindJpaRepository extends JpaRepository<SurveyEntity, Long> {

}

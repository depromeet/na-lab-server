package me.nalab.survey.jpa.adaptor.findspecific;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.nalab.core.data.survey.SurveyEntity;

@Repository("findspecific.TestSurveyFindJpaRepository")
public interface TestSurveyFindJpaRepository extends JpaRepository<SurveyEntity, Long> {

}

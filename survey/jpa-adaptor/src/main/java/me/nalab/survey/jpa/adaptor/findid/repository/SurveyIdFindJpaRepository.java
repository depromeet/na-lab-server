package me.nalab.survey.jpa.adaptor.findid.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import me.nalab.core.data.survey.SurveyEntity;

public interface SurveyIdFindJpaRepository extends JpaRepository<SurveyEntity, Long> {

	@Query("select s.id from SurveyEntity s where s.targetId = :targetId")
	List<Long> findIdByTargetId(@Param("targetId") Long targetId);

}

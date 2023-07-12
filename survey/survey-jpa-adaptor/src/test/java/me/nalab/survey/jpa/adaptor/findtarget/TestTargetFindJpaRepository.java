package me.nalab.survey.jpa.adaptor.findtarget;

import org.springframework.data.jpa.repository.JpaRepository;

import me.nalab.core.data.target.TargetEntity;

public interface TestTargetFindJpaRepository extends JpaRepository<TargetEntity, Long> {

}

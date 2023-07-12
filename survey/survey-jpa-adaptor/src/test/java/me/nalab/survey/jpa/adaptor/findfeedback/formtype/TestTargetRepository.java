package me.nalab.survey.jpa.adaptor.findfeedback.formtype;

import org.springframework.data.jpa.repository.JpaRepository;

import me.nalab.core.data.target.TargetEntity;

public interface TestTargetRepository extends JpaRepository<TargetEntity, Long> {

}

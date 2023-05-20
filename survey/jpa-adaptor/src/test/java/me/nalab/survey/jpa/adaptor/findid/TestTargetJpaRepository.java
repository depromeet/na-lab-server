package me.nalab.survey.jpa.adaptor.findid;

import org.springframework.data.jpa.repository.JpaRepository;

import me.nalab.core.data.target.TargetEntity;

public interface TestTargetJpaRepository extends JpaRepository<TargetEntity, Long> {
}

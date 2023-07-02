package me.nalab.survey.jpa.adaptor.find;

import org.springframework.data.jpa.repository.JpaRepository;

import me.nalab.core.data.target.TargetEntity;

public interface TestTargetFindRepository extends JpaRepository<TargetEntity, Long> {
}

package me.nalab.survey.jpa.adaptor.create.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.nalab.core.data.target.TargetEntity;

public interface TargetExistJpaRepository extends JpaRepository<TargetEntity, Long> {
}

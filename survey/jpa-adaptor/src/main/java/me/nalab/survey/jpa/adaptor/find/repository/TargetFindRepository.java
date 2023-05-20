package me.nalab.survey.jpa.adaptor.find.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.nalab.core.data.target.TargetEntity;

public interface TargetRepository extends JpaRepository<TargetEntity, Long> {
}
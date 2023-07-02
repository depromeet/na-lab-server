package me.nalab.survey.jpa.adaptor.create.repository;

import me.nalab.core.data.target.TargetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TargetCreateJpaRepository extends JpaRepository<TargetEntity, Long> {
}

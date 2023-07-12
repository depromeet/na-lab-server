package me.nalab.survey.jpa.adaptor.findtarget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.nalab.core.data.target.TargetEntity;

@Repository("findtarget.TargetFindJpaRepository")
public interface TargetFindJpaRepository extends JpaRepository<TargetEntity, Long> {

}

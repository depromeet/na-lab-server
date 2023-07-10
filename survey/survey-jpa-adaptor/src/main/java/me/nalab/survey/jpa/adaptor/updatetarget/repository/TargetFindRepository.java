package me.nalab.survey.jpa.adaptor.updatetarget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.nalab.core.data.target.TargetEntity;

@Repository("updatetarget.TargetFindRepository")
public interface TargetFindRepository extends JpaRepository<TargetEntity, Long> {

}

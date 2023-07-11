package me.nalab.survey.jpa.adaptor.updatetarget;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.nalab.core.data.target.TargetEntity;

@Repository("updatetarget.TestTargetFindRepository")
public interface TestTargetFindRepository extends JpaRepository<TargetEntity, Long> {

}

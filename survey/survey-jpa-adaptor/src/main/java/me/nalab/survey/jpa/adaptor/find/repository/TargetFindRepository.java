package me.nalab.survey.jpa.adaptor.find.repository;

import me.nalab.core.data.target.TargetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TargetFindRepository extends JpaRepository<TargetEntity, Long> {
    List<TargetEntity> findAllByNicknameOrderByCreatedAt(String nickname);
}

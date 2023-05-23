package me.nalab.survey.jpa.adaptor.createfeedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.nalab.core.data.feedback.FeedbackEntity;

public interface FeedbackCreateJpaRepository extends JpaRepository<FeedbackEntity, Long> {
}

package me.nalab.survey.jpa.adaptor.findfeedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.nalab.core.data.feedback.FeedbackEntity;

@Repository("findfeedback.FeedbackUpdateRepository")
public interface FeedbackUpdateRepository extends JpaRepository<FeedbackEntity, Long> {
}

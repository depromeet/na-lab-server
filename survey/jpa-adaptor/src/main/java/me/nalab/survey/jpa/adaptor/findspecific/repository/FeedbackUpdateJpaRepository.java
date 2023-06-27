package me.nalab.survey.jpa.adaptor.findspecific.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.nalab.core.data.feedback.FeedbackEntity;

@Repository("findspecific.FeedbackUpdateJpaRepository")
public interface FeedbackUpdateJpaRepository extends JpaRepository<FeedbackEntity, Long> {
}

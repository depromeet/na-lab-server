package me.nalab.survey.jpa.adaptor.findfeedbacksummary;

import org.springframework.data.jpa.repository.JpaRepository;

import me.nalab.core.data.feedback.FeedbackEntity;

public interface TestFeedbackCountJpaRepository extends JpaRepository<FeedbackEntity, Long> {


}

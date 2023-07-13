package me.nalab.survey.jpa.adaptor.findfeedbacksummary;

import org.springframework.data.jpa.repository.JpaRepository;

import me.nalab.core.data.feedback.ReviewerEntity;

public interface TestReviewerSaveRepository extends JpaRepository<ReviewerEntity, Long> {

}

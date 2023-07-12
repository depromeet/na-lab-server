package me.nalab.survey.jpa.adaptor.bookmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.nalab.core.data.feedback.FormFeedbackEntity;

public interface FormQuestionFeedbackUpdateRepository extends JpaRepository<FormFeedbackEntity, Long> {
}

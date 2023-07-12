package me.nalab.survey.jpa.adaptor.findfeedback.formtype;

import org.springframework.data.jpa.repository.JpaRepository;

import me.nalab.core.data.feedback.FeedbackEntity;

public interface TestFeedbackFindRepository extends JpaRepository<FeedbackEntity, Long> {

}

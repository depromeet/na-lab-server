package me.nalab.survey.jpa.adaptor.findspecific;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.nalab.core.data.feedback.FeedbackEntity;

@Repository("findspecific.TestFeedbackFindJpaRepository")
public interface TestFeedbackFindJpaRepository extends JpaRepository<FeedbackEntity, Long> {

}

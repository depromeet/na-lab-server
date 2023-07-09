package me.nalab.survey.jpa.adaptor.bookmark;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.nalab.core.data.feedback.FeedbackEntity;

@Repository("bookmark.TestFeedbackSaveJpaRepository")
public interface TestFeedbackSaveJpaRepository extends JpaRepository<FeedbackEntity, Long> {
}

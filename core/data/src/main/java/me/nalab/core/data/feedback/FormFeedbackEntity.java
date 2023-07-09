package me.nalab.core.data.feedback;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "form_feedback")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class FormFeedbackEntity {

	@Id
	@Column(name = "form_feedback_id")
	protected Long id;

	@Column(name = "form_question_id")
	@JoinColumn(name = "form_question_id", nullable = false)
	protected Long questionId;

	@Column(name = "is_read")
	protected boolean isRead;

	@ManyToOne
	@JoinColumn(name = "feedback_id", nullable = false)
	protected FeedbackEntity feedbackEntity;

	@Column(name = "is_bookmarked", nullable = false)
	protected boolean isBookmarked;

	@Column(name = "bookmarked_at", columnDefinition = "TIMESTAMP(6)", nullable = false)
	protected Instant bookmarkedAt;

	@PrePersist
	void prePersist() {
		Instant now = Instant.now();
		bookmarkedAt = bookmarkedAt != null ? bookmarkedAt : now;
	}

	@PreUpdate
	void preUpdate() {
		Instant now = Instant.now();
		bookmarkedAt = now;
	}
}

package me.nalab.core.data.feedback;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	@Column(name = "question_id")
	protected Long questionId;

	@Column(name = "is_read")
	protected boolean isRead;

	@ManyToOne
	@JoinColumn(name = "feedback_id")
	protected FeedbackEntity feedbackEntity;

}

package me.nalab.core.data.feedback;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import me.nalab.core.data.common.TimeBaseEntity;

@Entity
@Table(name = "feedback")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackEntity extends TimeBaseEntity {

	@Id
	@Column(name = "feedback_id")
	private Long id;

	@JoinColumn(name = "survey_id", nullable = false)
	private Long surveyId;

	@JoinColumn(name = "is_read", nullable = false)
	private boolean isRead;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "reviewer_id")
	private ReviewerEntity reviewer;

	@OneToMany(mappedBy = "feedbackEntity", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<FormFeedbackEntity> formFeedbackEntityList;

	public FeedbackEntity(FeedbackEntityBuilder<?, ?> b) {
		super(b);
		this.id = b.id;
		this.surveyId = b.surveyId;
		this.isRead = b.isRead;
		this.reviewer = b.reviewer;
		this.formFeedbackEntityList = b.formFeedbackEntityList;
		cascadeFeedback();
	}

	private void cascadeFeedback() {
		formFeedbackEntityList.forEach(
			f -> {
				if(f.getFeedbackEntity() != this) {
					f.setFeedbackEntity(this);
				}
			}
		);
	}

}

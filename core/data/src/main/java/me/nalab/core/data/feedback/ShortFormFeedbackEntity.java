package me.nalab.core.data.feedback;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ShortFormFeedbackEntity extends FormFeedbackEntity {

	@ElementCollection
	@CollectionTable(name = "reply", joinColumns = @JoinColumn(name = "form_feedback_id"))
	@Column(name = "replies")
	private List<String> replyList;

}

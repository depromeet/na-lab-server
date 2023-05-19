package me.nalab.core.data.survey;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import me.nalab.core.data.common.TimeBaseEntity;

@Entity
@Table(name = "survey")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class SurveyEntity extends TimeBaseEntity {

	@Id
	@Column(name = "survey_id")
	private Long id;

	@OneToMany(mappedBy = "survey", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<FormQuestionEntity> formQuestionableList;

	@JoinColumn(name = "target_id", nullable = false)
	private Long targetId;

	public SurveyEntity(TimeBaseEntityBuilder<?, ?> b, Long id, List<FormQuestionEntity> formQuestionableList,
		Long targetId) {
		super(b);
		this.id = id;
		this.targetId = targetId;
		this.formQuestionableList = formQuestionableList;
		cascadeSurvey();
	}

	private void cascadeSurvey() {
		for(FormQuestionEntity formQuestionEntity : formQuestionableList) {
			if(formQuestionEntity.getSurvey() == this) {
				continue;
			}
			formQuestionEntity.setSurvey(this);
		}
	}

}

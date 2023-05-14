package me.nalab.core.data.survey;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import me.nalab.core.data.common.TimeBaseEntity;
import me.nalab.core.data.target.Target;

@Entity
@Table(name = "survey")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class Survey extends TimeBaseEntity {

	@Id
	@Column(name = "survey_id")
	private Long id;

	@Size(min = 5, max = 25)
	@OneToMany(mappedBy = "survey", fetch = FetchType.LAZY)
	private List<FormQuestion> formQuestionableList;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "target_id", nullable = false)
	private Target target;

}

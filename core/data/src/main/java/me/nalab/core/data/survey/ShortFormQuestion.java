package me.nalab.core.data.survey;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

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
public class ShortFormQuestion extends FormQuestion {

	@Enumerated(EnumType.STRING)
	private ShortFormQuestionType shortFormQuestionType;

}

package me.nalab.survey.domain.target;

import java.util.List;
import java.util.function.LongSupplier;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.nalab.survey.domain.exception.IdAlreadyGeneratedException;
import me.nalab.survey.domain.support.IdGeneratable;
import me.nalab.survey.domain.survey.Survey;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class Target implements IdGeneratable {

	private Long id;
	private final List<Survey> surveyList;
	private final String nickname;
	private String position;

	@Override
	public void withId(LongSupplier idSupplier) {
		if (id != null) {
			throw new IdAlreadyGeneratedException(this);
		}
		id = idSupplier.getAsLong();
	}

	public void setPosition(String position) {
		this.position = position;
	}
}

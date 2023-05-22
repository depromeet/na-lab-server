package me.nalab.survey.domain.feedback;

import java.util.function.LongSupplier;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.nalab.survey.domain.exception.IdAlreadyGeneratedException;
import me.nalab.survey.domain.support.IdGeneratable;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class Reviewer implements IdGeneratable {

	private Long id;
	private final String nickName;
	private final boolean collaborationExperience;
	private final String position;

	@Override
	public void withId(LongSupplier idSupplier) {
		if(this.id != null) {
			throw new IdAlreadyGeneratedException(this);
		}
		id = idSupplier.getAsLong();
	}

}

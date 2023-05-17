package me.nalab.survey.domain.survey;

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
public class Choice implements IdGeneratable {

	private Long id;
	private final String content;
	private final Integer order;

	@Override
	public void withId(LongSupplier idSupplier) {
		if(id != null) {
			throw new IdAlreadyGeneratedException(this);
		}
		id = idSupplier.getAsLong();
	}

}

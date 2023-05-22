package me.nalab.survey.domain.survey;

import java.util.Comparator;
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
public class Choice implements IdGeneratable, Comparable<Choice> {

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

	@Override
	public int compareTo(Choice other) {
		return Comparator.comparingInt(Choice::getOrder)
			.compare(this, other);
	}
}

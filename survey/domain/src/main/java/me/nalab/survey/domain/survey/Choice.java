package me.nalab.survey.domain.survey;

import java.util.Objects;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class Choice {

	private final Long id;
	private final String content;
	private final Integer order;

	@Override
	public String toString() {
		return "Choice{" +
			"id=" + id +
			", content='" + content + '\'' +
			", order=" + order +
			'}';
	}

}

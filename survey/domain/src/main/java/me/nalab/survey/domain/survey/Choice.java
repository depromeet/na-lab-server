package me.nalab.survey.domain.survey;

import java.util.Objects;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
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

	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		Choice choice = (Choice)o;
		return id.equals(choice.id) && content.equals(choice.content) && order.equals(choice.order);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, content, order);
	}
}

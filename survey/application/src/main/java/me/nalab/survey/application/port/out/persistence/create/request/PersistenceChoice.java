package me.nalab.survey.application.port.out.persistence.create.request;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class PersistenceChoice {

	private final Long id;
	private final String content;
	private final Integer order;

}

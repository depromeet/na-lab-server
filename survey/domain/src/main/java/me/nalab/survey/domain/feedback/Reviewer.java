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
	private String nickName;
	private final boolean collaborationExperience;
	private final String position;

	public void generateFirstReviewerNickName() {
		nickName = "A";
	}

	public void generateNickName(String lastName) {
		nickName = getNextNickName(lastName);
	}

	private String getNextNickName(String lastName) {
		for(int i = lastName.length() - 1; i >= 0; i--) {
			if(lastName.charAt(i) != 'Z') {
				return lastName.substring(0, i) + (char)(lastName.charAt(i) + 1) + "A".repeat(lastName.length() - (i + 1));
			}
		}
		return "A".repeat(Math.max(0, lastName.length() + 1));
	}

	@Override
	public void withId(LongSupplier idSupplier) {
		if(this.id != null) {
			throw new IdAlreadyGeneratedException(this);
		}
		id = idSupplier.getAsLong();
	}

}

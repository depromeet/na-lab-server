package me.nalab.survey.domain.feedback;

import java.time.Instant;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class Bookmark {

	private boolean isBookmarked;
	private Instant bookmarkedAt;

	public void replaceIsBookmarked() {
		this.isBookmarked = !isBookmarked;
		this.bookmarkedAt = Instant.now();
	}

}

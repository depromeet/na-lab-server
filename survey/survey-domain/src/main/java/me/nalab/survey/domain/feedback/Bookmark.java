package me.nalab.survey.domain.feedback;

import java.time.Instant;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Bookmark {

	private boolean isBookmarked;
	private final Instant bookmarkedAt;

	public void replaceIsBookmarked() {
		this.isBookmarked = !isBookmarked;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Bookmark bookmark = (Bookmark)o;

		return isBookmarked == bookmark.isBookmarked;
	}

	@Override
	public int hashCode() {
		return Boolean.hashCode(isBookmarked);
	}

}

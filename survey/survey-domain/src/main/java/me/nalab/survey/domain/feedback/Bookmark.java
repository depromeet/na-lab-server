package me.nalab.survey.domain.feedback;

import java.time.Instant;
import java.util.Objects;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class Bookmark {

	private static final boolean BOOKMARK_DEFAULT_STATE = false;

	private boolean isBookmarked = BOOKMARK_DEFAULT_STATE;
	private Instant bookmarkedAt;

	public Bookmark(boolean isBookmarked, Instant bookmarkedAt) {
		Objects.requireNonNull(bookmarkedAt, () -> "Null value on new Bookmark(..., bookmarkedAt)");
		this.isBookmarked = isBookmarked;
		this.bookmarkedAt = bookmarkedAt;
	}

	public void replaceIsBookmarked() {
		this.isBookmarked = !isBookmarked;
		this.bookmarkedAt = Instant.now();
	}

}

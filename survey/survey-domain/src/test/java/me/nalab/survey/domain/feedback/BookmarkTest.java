package me.nalab.survey.domain.feedback;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BookmarkTest {

	@Test
	@DisplayName("replaceIsBookmarked()메소드는 bookmark가 false라면, true로 변경한다.")
	void bookmark_replacedIsBookmarked_true_if_false_test() {

		boolean initialIsBookmarked = false;
		Instant initialBookmarkedAt = Instant.now();

		Bookmark bookmark = Bookmark.builder()
			.isBookmarked(initialIsBookmarked)
			.bookmarkedAt(initialBookmarkedAt)
			.build();
		bookmark.replaceIsBookmarked();

		boolean updatedIsBookmarked = bookmark.isBookmarked();
		Instant updatedBookmarkedAt = bookmark.getBookmarkedAt();

		assertEquals(updatedIsBookmarked, !initialIsBookmarked);
		assertNotEquals(updatedBookmarkedAt, initialBookmarkedAt);
	}

	@Test
	@DisplayName("replaceIsBookmarked()메소드는 bookmark가 true라면, false로 변경한다.")
	void bookmark_replacedIsBookmarked_false_if_true_test() {

		boolean initialIsBookmarked = true;
		Instant initialBookmarkedAt = Instant.now();

		Bookmark bookmark = Bookmark.builder()
			.isBookmarked(initialIsBookmarked)
			.bookmarkedAt(initialBookmarkedAt)
			.build();
		bookmark.replaceIsBookmarked();

		boolean updatedIsBookmarked = bookmark.isBookmarked();
		Instant updatedBookmarkedAt = bookmark.getBookmarkedAt();

		assertEquals(updatedIsBookmarked, !initialIsBookmarked);
		assertNotEquals(updatedBookmarkedAt, initialBookmarkedAt);
	}

}

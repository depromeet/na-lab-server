package me.nalab.survey.domain.feedback;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;

import org.junit.jupiter.api.Test;

class BookmarkTest {

	@Test
	void bookmark_replacedIsBookmarked_test() {

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

}
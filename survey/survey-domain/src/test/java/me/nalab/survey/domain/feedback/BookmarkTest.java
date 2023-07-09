package me.nalab.survey.domain.feedback;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;

import org.junit.jupiter.api.Test;

class BookmarkTest {

	@Test
	void bookmark_replacedIsBookmarked_test() {

		boolean initialIsBookmarked = false;
		Bookmark bookmark = Bookmark.builder().isBookmarked(initialIsBookmarked).bookmarkedAt(Instant.now()).build();

		bookmark.replaceIsBookmarked();

		boolean updatedIsBookmarked = bookmark.isBookmarked();
		assertEquals(updatedIsBookmarked, !initialIsBookmarked);
	}

	@Test
	void bookmark_updateBookmarkedAt_test() {

		Instant initialBookmarkedAt = Instant.now();
		Bookmark bookmark = Bookmark.builder().isBookmarked(false).bookmarkedAt(initialBookmarkedAt).build();

		bookmark.updateBookmarkedAt();

		Instant updatedBookmarkedAt = bookmark.getBookmarkedAt();
		assertNotEquals(initialBookmarkedAt, updatedBookmarkedAt);
	}
}
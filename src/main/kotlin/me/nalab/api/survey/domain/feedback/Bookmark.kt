package me.nalab.api.survey.domain.feedback

import me.nalab.core.time.TimeUtil
import java.time.Instant
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Bookmark(
    @Column(name = "is_bookmarked", nullable = false)
    var isBookmarked: Boolean = BOOKMARK_DEFAULT_STATE,

    @Column(name = "bookmarked_at", columnDefinition = "TIMESTAMP(6)", nullable = false)
    var bookmarkedAt: Instant,
) {

    companion object {
        private const val BOOKMARK_DEFAULT_STATE = false

        fun impossible(): Bookmark = Bookmark(bookmarkedAt = TimeUtil.toInstant())
    }
}

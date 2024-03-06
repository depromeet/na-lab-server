package me.nalab.survey.domain.feedback

import java.time.Instant
import javax.persistence.*

@Entity
@Table(name = "form_feedback")
abstract class FormQuestionFeedbackable(
    @Id
    @Column(name = "form_feedback_id")
    protected open var id: Long? = null,

    @Column(name = "form_question_id")
    @JoinColumn(name = "form_question_id", nullable = false)
    protected open val formQuestionId: Long,

    @Column(name = "is_read")
    protected open var isRead: Boolean = false,

    @Embedded
    protected open val bookmark: Bookmark,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feedback_id")
    private var feedback: Feedback,
) {

    fun bookmark() {
        bookmark.isBookmarked = true
        bookmark.bookmarkedAt = Instant.now()
    }

    fun cancel() {
        bookmark.isBookmarked = false
        bookmark.bookmarkedAt = Instant.now()
    }
}

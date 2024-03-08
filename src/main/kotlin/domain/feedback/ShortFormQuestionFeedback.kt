package domain.feedback

import javax.persistence.*

@Entity
class ShortFormQuestionFeedback(
    id: Long,
    formQuestionId: Long,
    isRead: Boolean = false,
    bookmark: domain.feedback.Bookmark,
    feedback: domain.feedback.Feedback,

    @ElementCollection
    @CollectionTable(name = "reply", joinColumns = [JoinColumn(name = "form_feedback_id")])
    @Column(name = "replies")
    val replies: MutableList<String>,
) : domain.feedback.FormQuestionFeedbackable(id, formQuestionId, isRead, bookmark, feedback)

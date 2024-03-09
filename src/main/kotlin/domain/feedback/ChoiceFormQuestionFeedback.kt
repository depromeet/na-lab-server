package domain.feedback

import javax.persistence.*

@Entity
class ChoiceFormQuestionFeedback(
    id: Long,
    formQuestionId: Long,
    isRead: Boolean = false,
    bookmark: domain.feedback.Bookmark = domain.feedback.Bookmark.Companion.impossible(),
    feedback: domain.feedback.Feedback,

    @ElementCollection
    @Column(name = "selects")
    @CollectionTable(name = "selects", joinColumns = [JoinColumn(name = "form_feedback_id")])
    private val selectedChoiceIds: MutableSet<Long>,
) : domain.feedback.FormQuestionFeedbackable(id, formQuestionId, isRead, bookmark, feedback)

package me.nalab.survey.domain.feedback

import javax.persistence.*

@Entity
class ShortFormQuestionFeedback(
    id: Long,
    formQuestionId: Long,
    isRead: Boolean = false,
    bookmark: Bookmark,
    feedback: Feedback,

    @ElementCollection
    @CollectionTable(name = "reply", joinColumns = [JoinColumn(name = "form_feedback_id")])
    @Column(name = "replies")
    val replies: MutableList<String>,
) : FormQuestionFeedbackable(id, formQuestionId, isRead, bookmark, feedback)

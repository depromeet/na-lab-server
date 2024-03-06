package me.nalab.survey.domain.feedback

import javax.persistence.*

@Entity
@Table(name = "form_feedback")
abstract class FormQuestionFeedbackable(
    @Id
    @Column(name = "form_feedback_id")
    open val id: Long,

    @Column(name = "form_question_id")
    @JoinColumn(name = "form_question_id", nullable = false)
    open val formQuestionId: Long,

    @Column(name = "is_read")
    open var isRead: Boolean = false,

    @Embedded
    open val bookmark: Bookmark,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feedback_id")
    val feedback: Feedback,
)

package me.nalab.survey.domain.feedback

import me.nalab.core.data.common.TimeBaseEntity
import javax.persistence.*

@Entity(name = "feedback")
@Table(name = "feedback")
class Feedback(
    @Id
    @Column(name = "feedback_id")
    private var id: Long? = null,

    @JoinColumn(name = "survey_id", nullable = false)
    private val surveyId: Long,

    @OneToMany(
        mappedBy = "feedback",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.PERSIST, CascadeType.MERGE]
    )
    private val allQuestionFeedbacks: MutableList<FormQuestionFeedbackable>,

    @Column(name = "is_read", nullable = false)
    private var isRead: Boolean = false,

    @JoinColumn(name = "reviewer_id", nullable = false)
    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    private val reviewer: Reviewer,
) : Comparable<Feedback>, TimeBaseEntity() {

    fun read(read: Boolean) {
        isRead = read
    }

    override fun compareTo(other: Feedback): Int {
        if (updatedAt.isAfter(other.updatedAt)) {
            return -1
        }
        if (updatedAt.isBefore(other.updatedAt)) {
            return 1
        }
        if (createdAt.isAfter(other.createdAt)) {
            return -1
        }
        if (createdAt.isBefore(other.createdAt)) {
            return 1
        }
        return 0
    }
}

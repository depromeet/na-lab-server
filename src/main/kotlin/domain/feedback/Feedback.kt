package domain.feedback

import me.nalab.core.data.common.TimeBaseEntity
import javax.persistence.*

@Entity(name = "feedback")
@Table(name = "feedback")
class Feedback(
    @Id
    @Column(name = "feedback_id")
    val id: Long,

    @JoinColumn(name = "survey_id", nullable = false)
    val surveyId: Long,

    @OneToMany(
        mappedBy = "feedback",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.PERSIST, CascadeType.MERGE]
    )
    val questionFeedbacks: MutableList<domain.feedback.FormQuestionFeedbackable> = mutableListOf(),

    @Column(name = "is_read", nullable = false)
    private var isRead: Boolean = false,

    @JoinColumn(name = "reviewer_id", nullable = false)
    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    val reviewer: domain.feedback.Reviewer,
) : Comparable<domain.feedback.Feedback>, TimeBaseEntity() {

    override fun compareTo(other: domain.feedback.Feedback): Int {
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

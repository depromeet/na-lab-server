package me.nalab.api.survey.domain.feedback

import me.nalab.api.core.TimeBaseEntity
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
    val questionFeedbacks: MutableList<FormQuestionFeedbackable> = mutableListOf(),

    @Column(name = "is_read", nullable = false)
    private var isRead: Boolean = false,

    @JoinColumn(name = "reviewer_id", nullable = false)
    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    val reviewer: Reviewer,
) : Comparable<Feedback>, TimeBaseEntity() {

    override fun compareTo(other: Feedback): Int {
        return DEFAULT_COMPARATOR.compare(this, other)
    }

    companion object {
        val DEFAULT_COMPARATOR =
            compareByDescending<Feedback> { it.updatedAt }.thenByDescending { it.createdAt }
    }
}

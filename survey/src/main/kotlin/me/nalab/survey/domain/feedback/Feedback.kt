package me.nalab.survey.domain.feedback

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
    val allQuestionFeedbacks: MutableList<FormQuestionFeedbackable>,

    @Column(name = "is_read", nullable = false)
    private var isRead: Boolean = false,

    @JoinColumn(name = "reviewer_id", nullable = false)
    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    val reviewer: Reviewer,
) : Comparable<Feedback>, TimeBaseEntity() {

    fun read(read: Boolean) {
        isRead = read
    }

    fun generateFirstReviewerNickName() {
        reviewer.nickName = "A"
    }

    fun generateNickName(lastName: String) {
        reviewer.nickName = getNextNickName(lastName)
    }

    private fun getNextNickName(lastName: String): String {
        for (i in lastName.length - 1 downTo 0) {
            if (lastName[i] != 'Z') {
                return lastName.substring(0, i) + (lastName[i].code + 1).toChar() + "A".repeat(
                    lastName.length - (i + 1)
                )
            }
        }
        return "A".repeat(Math.max(0, lastName.length + 1))
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

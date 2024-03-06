package me.nalab.survey.domain.feedback

import me.nalab.core.data.common.TimeBaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity(name = "reviewer")
@Table(name = "reviewer")
class Reviewer(
    @Id
    @Column(name = "reviewer_id")
    private var id: Long? = null,

    @Column(name = "nick_name", nullable = false)
    private var nickName: String,

    @Column(name = "collaboration_experience", nullable = false)
    private val collaborationExperience: Boolean,

    @Column(name = "position", nullable = false)
    private var position: String? = null,
) : TimeBaseEntity() {

    fun generateFirstReviewerNickName() {
        nickName = "A"
    }

    fun generateNickName(lastName: String) {
        nickName = getNextNickName(lastName)
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
}

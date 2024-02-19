package me.nalab.gallery.domain

import me.nalab.core.data.common.TimeBaseEntity
import javax.persistence.*

@Entity
@Table(
    name = "gallery",
    indexes = [Index(columnList = "user_id")],
)
class Gallery(
    @Id
    @Column(name = "id")
    val id: Long,

    @Embedded
    private val user: User,

    @Embedded
    private val survey: Survey,

    @Version
    private val version: Long? = null,
) : TimeBaseEntity() {

    fun userId(): Long = user.id

    fun userName(): String = user.name

    fun userNickname(): String = user.nickname

    fun surveyId(): Long = survey.id

    fun surveyFeedbackCount(): Int = survey.feedbackCount

    fun userImageUrl(): String = user.userImageUrl

    fun surveySaveCount(): Int = survey.saveCount

    fun feedback(): String = survey.feedback.reply

    fun tendencies(): List<Tendency> = ArrayList(survey.tendencies)
}

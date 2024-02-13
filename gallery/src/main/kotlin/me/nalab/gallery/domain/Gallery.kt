package me.nalab.gallery.domain

import me.nalab.core.data.common.TimeBaseEntity
import javax.persistence.*

@Entity
@Table(name = "gallery")
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

    fun userPosition(): String = user.position

    fun surveyId(): Long = survey.id

    fun surveyFeedbackCount(): Int = survey.feedbackCount

    fun surveySaveCount(): Int = survey.saveCount

    fun increaseFeedbackCount() = survey.feedbackCount++

    fun increaseSaveCount() = survey.saveCount++
}

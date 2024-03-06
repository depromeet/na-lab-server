package me.nalab.gallery.domain

import me.nalab.core.data.common.TimeBaseEntity
import me.nalab.core.time.TimeUtil
import java.time.Instant
import javax.persistence.*

@Entity
@Table(name = "gallery")
class Gallery(
    @Id
    @Column(name = "gallery_id")
    val id: Long,

    @Embedded
    val target: Target,

    @Embedded
    val survey: Survey,

    @Column(name = "update_order", columnDefinition = "TIMESTAMP(6)", nullable = false)
    private var updateOrder: Instant,

    @Version
    private var version: Long? = null,
) : TimeBaseEntity() {

    constructor(
        id: Long,
        targetId: Long,
        job: Job,
        surveyId: Long,
        bookmarkedCount: Int = 0,
        updateOrder: Instant = TimeUtil.toInstant(),
    ): this(id, Target(targetId, job), Survey(surveyId, bookmarkedCount), updateOrder)

    fun getTargetId(): Long = target.targetId

    fun getJob(): Job = target.job

    fun getBookmarkedCount(): Int = survey.bookmarkedCount

    fun setBookmarkedCount(bookmarkedCount: Int) {
        survey.bookmarkedCount = bookmarkedCount
    }
}

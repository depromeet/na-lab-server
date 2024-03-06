package me.nalab.survey.domain.survey

import me.nalab.core.data.common.TimeBaseEntity
import javax.persistence.*

@Entity
@Table(name = "target")
class Target(
    @Id
    @Column(name = "target_id")
    private var id: Long? = null,

    @Column(name = "target_name", nullable = false)
    private val nickname: String,

    @Column(name = "job", columnDefinition = "TEXT")
    private val job: String,

    @Column(name = "image_url", columnDefinition = "TEXT")
    private val imageUrl: String,

    @Column(name = "position")
    private var position: String? = null,

    @ElementCollection
    @CollectionTable(name = "bookmarked_survey", joinColumns = [JoinColumn(name = "target_id")])
    private val bookmarkedSurveys: MutableSet<SurveyBookmark> = NONE_BOOKMARKED_SURVEYS,

    @Version
    @Column(name = "version")
    private var version: Long? = null
) : TimeBaseEntity() {

    fun setPosition(position: String) {
        this.position = position
    }

    fun bookmark(surveyId: Long) {
        val bookmark = SurveyBookmark(surveyId)
        bookmarkedSurveys.add(bookmark)
    }

    companion object {
        private val NONE_BOOKMARKED_SURVEYS: MutableSet<SurveyBookmark> = HashSet()
    }
}

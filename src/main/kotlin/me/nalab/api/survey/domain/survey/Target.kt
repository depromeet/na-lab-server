package me.nalab.api.survey.domain.survey

import me.nalab.api.core.TimeBaseEntity
import me.nalab.api.survey.domain.survey.value.ImageUrl
import me.nalab.api.survey.domain.survey.value.Job
import me.nalab.api.survey.domain.survey.value.Position
import javax.persistence.*

@Entity
@Table(name = "target")
class Target(
    @Id
    @Column(name = "target_id")
    val id: Long,

    @Column(name = "target_name", nullable = false)
    val nickname: String,

    @Embedded
    val job: Job = Job.empty(),

    @Embedded
    val imageUrl: ImageUrl = ImageUrl.empty(),

    @Column(name = "position")
    private var position: Position = Position.empty(),

    @OneToOne(mappedBy = "target", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val survey: Survey,

    @ElementCollection
    @CollectionTable(name = "bookmarked_survey", joinColumns = [JoinColumn(name = "target_id")])
    val bookmarkedSurveys: MutableSet<SurveyBookmark> = NO_BOOKMARKED_SURVEYS,

    @Version
    @Column(name = "version")
    private var version: Long? = null
) : TimeBaseEntity() {

    companion object {
        private val NO_BOOKMARKED_SURVEYS: MutableSet<SurveyBookmark> = HashSet()
    }
}

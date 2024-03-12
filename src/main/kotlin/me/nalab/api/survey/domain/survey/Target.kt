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

    @Column(name = "job", nullable = false)
    val job: Job = Job.empty(),

    @Column(name = "image_url", nullable = false)
    val imageUrl: ImageUrl = ImageUrl.empty(),

    @Column(name = "position", nullable = false)
    private var position: Position = Position.empty(),

    @OneToOne(mappedBy = "target", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val survey: Survey,

    @ElementCollection
    @CollectionTable(name = "bookmarked_survey", joinColumns = [JoinColumn(name = "target_id")])
    val bookmarkedSurveys: MutableSet<SurveyBookmark> = HashSet(),

    @Version
    @Column(name = "version")
    private var version: Long? = null
) : TimeBaseEntity()

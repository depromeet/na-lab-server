package domain.survey.value

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Job(
    @Column(name = "job", nullable = false)
    val value: String,
) {

    companion object {
        fun empty(): Job = Job("")
    }
}

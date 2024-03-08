package domain.survey

import me.nalab.core.data.common.TimeBaseEntity
import me.nalab.survey.domain.survey.exception.DuplicatedOrderException
import javax.persistence.*

@Table(name = "survey")
@Entity(name = "survey")
class Survey(
    @Id
    @Column(name = "survey_id")
    val id: Long,

    @OneToMany(
        mappedBy = "survey",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.PERSIST, CascadeType.MERGE]
    )
    val formQuestionables: MutableList<FormQuestionable> = mutableListOf(),

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_id", nullable = false)
    val target: Target,
) : TimeBaseEntity() {

    init {
        validNoDuplicatedFormOrder()
    }

    private fun validNoDuplicatedFormOrder() {
        val orders = HashSet<Int>()
        this.formQuestionables.forEach { formQuestion ->
            val order: Int = formQuestion.order
            if (orders.contains(order)) {
                throw DuplicatedOrderException(order, orders)
            }
            orders.add(order)
        }
    }
}

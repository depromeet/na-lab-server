package domain.survey

import me.nalab.survey.domain.survey.exception.DuplicatedOrderException
import javax.persistence.*

@Entity
class ChoiceFormQuestion(
    id: Long,
    title: String,
    order: Int,
    questionType: QuestionType,
    survey: Survey,

    @OneToMany(
        mappedBy = "choiceFormQuestion",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.PERSIST, CascadeType.MERGE],
    )
    val choices: MutableList<Choice>,

    @Column(name = "max_selection_count")
    val maxSelectableCount: Int,

    @Enumerated(EnumType.STRING)
    @Column(name = "choice_question_type")
    val choiceFormQuestionType: ChoiceFormQuestionType,
) : FormQuestionable(id, title, order, questionType, survey) {

    init {
        choices.sorted()
        validNoDuplicatedChoiceOrder(choices)
    }

    private fun validNoDuplicatedChoiceOrder(choiceList: List<Choice>) {
        val orders = HashSet<Int>()
        choiceList.forEach { choice ->
            val order: Int = choice.order
            if (orders.contains(order)) {
                throw DuplicatedOrderException(order, orders)
            }
            orders.add(order)
        }
    }
}

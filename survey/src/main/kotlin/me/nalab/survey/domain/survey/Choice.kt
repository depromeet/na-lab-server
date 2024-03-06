package me.nalab.survey.domain.survey

import javax.persistence.*

@Entity(name = "choice")
@Table(name = "choice")
class Choice(

    @Id
    @Column(name = "choice_id")
    private var id: Long? = null,

    @Column(name = "content", length = 18, nullable = false)
    private val content: String,

    @Column(name = "orders", nullable = false)
    val order: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_question_id", nullable = false)
    private val choiceFormQuestion: ChoiceFormQuestion,
) : Comparable<Choice> {

    override fun compareTo(other: Choice): Int {
        return Comparator.comparingInt { obj: Choice -> obj.order }
            .compare(this, other)
    }
}

package me.nalab.api.survey.domain.survey

import me.nalab.api.core.TimeBaseEntity
import javax.persistence.*

@Entity
@Table(name = "form_question")
abstract class FormQuestionable(
    @Id
    @Column(name = "form_question_id")
    val id: Long,

    @Column(name = "title", nullable = false, length = 45)
    val title: String,

    @Column(name = "orders", nullable = false)
    val order: Int,

    @Enumerated(EnumType.STRING)
    @Column(name = "question_type")
    val questionType: QuestionType,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id", nullable = false)
    val survey: Survey,
) : Comparable<FormQuestionable>, TimeBaseEntity() {

    override fun compareTo(other: FormQuestionable): Int = this.order.compareTo(other.order)
}

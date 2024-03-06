package me.nalab.survey.domain.survey

import javax.persistence.*

@Entity
class ShortFormQuestion(
    id: Long? = null,
    title: String,
    order: Int,
    questionType: QuestionType,
    survey: Survey,

    @Enumerated(EnumType.STRING)
    @Column(name = "short_form_question_type")
    private val shortFormQuestionType: ShortFormQuestionType,
) : FormQuestionable(id, title, order, questionType, survey)

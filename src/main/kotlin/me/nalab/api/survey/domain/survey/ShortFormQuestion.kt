package me.nalab.api.survey.domain.survey

import javax.persistence.*

@Entity
class ShortFormQuestion(
    id: Long,
    title: String,
    order: Int,
    questionType: QuestionType,
    survey: Survey,

    @Enumerated(EnumType.STRING)
    @Column(name = "short_form_question_type")
    val shortFormQuestionType: ShortFormQuestionType,
) : FormQuestionable(id, title, order, questionType, survey)

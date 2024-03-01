package me.nalab.gallery.app

import me.nalab.gallery.domain.GalleryService
import me.nalab.gallery.domain.response.GalleryDto
import me.nalab.survey.application.port.`in`.web.findfeedback.FeedbackFindUseCase
import me.nalab.survey.application.port.`in`.web.survey.find.SurveyFindUseCase
import me.nalab.survey.application.port.`in`.web.target.find.TargetFindUseCase
import org.springframework.stereotype.Service

@Service
class GalleryGetApp(
    private val targetFindUseCase: TargetFindUseCase,
    private val surveyFindUseCase: SurveyFindUseCase,
    private val feedbackFindUseCase: FeedbackFindUseCase,
    private val galleryService: GalleryService,
) {

    fun getGalleryByTargetId(targetId: Long): GalleryDto {
        val gallery = galleryService.getGalleryByTargetId(targetId)
        val target = targetFindUseCase.findTarget(targetId)
        val survey = surveyFindUseCase.getSurveyByTargetId(targetId)
        val feedbacks = feedbackFindUseCase.findAllFeedbackDtoBySurveyId(survey.id)

        return toGalleryDto(gallery, target, survey, feedbacks)
    }
}

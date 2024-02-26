package me.nalab.gallery.app

import me.nalab.core.idgenerator.idcore.IdGenerator
import me.nalab.core.time.TimeUtil
import me.nalab.gallery.domain.Gallery
import me.nalab.gallery.domain.GalleryService
import me.nalab.gallery.domain.Job
import me.nalab.gallery.domain.response.GalleryDto
import me.nalab.survey.application.common.survey.dto.SurveyDto
import me.nalab.survey.application.common.survey.dto.TargetDto
import me.nalab.survey.application.port.`in`.web.findfeedback.FeedbackFindUseCase
import me.nalab.survey.application.port.`in`.web.survey.find.SurveyFindUseCase
import me.nalab.survey.application.port.`in`.web.target.find.TargetFindUseCase
import org.springframework.stereotype.Service

@Service
class GalleryRegisterApp(
    private val idGenerator: IdGenerator,
    private val targetFindUseCase: TargetFindUseCase,
    private val surveyFindUseCase: SurveyFindUseCase,
    private val feedbackFindUseCase: FeedbackFindUseCase,
    private val galleryService: GalleryService,
) {

    fun registerGalleryByTargetId(targetId: Long, job: String): GalleryDto {
        val target = targetFindUseCase.findTarget(targetId)
        val survey = surveyFindUseCase.getSurveyByTargetId(targetId)

        val gallery = galleryService.registerGallery(toGallery(job, target, survey))

        val feedbacks = feedbackFindUseCase.findAllFeedbackDtoBySurveyId(survey.id)

        return toGalleryDto(gallery, target, survey, feedbacks)
    }

    private fun toGallery(
        job: String,
        target: TargetDto,
        survey: SurveyDto,
    ): Gallery {
        return Gallery(
            id = idGenerator.generate(),
            targetId = target.id,
            job = Job.valueOf(job.uppercase()),
            surveyId = survey.id,
            bookmarkedCount = target.bookmarkedSurveys.size,
            updateOrder = TimeUtil.toInstant(),
        )
    }

}

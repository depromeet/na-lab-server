package me.nalab.survey.application.service.bookmark;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.common.survey.dto.SurveyBookmarkDto;
import me.nalab.survey.application.exception.SurveyDoesNotExistException;
import me.nalab.survey.application.exception.TargetDoesNotExistException;
import me.nalab.survey.application.port.in.web.bookmark.SurveyBookmarkReplaceUseCase;
import me.nalab.survey.application.port.out.persistence.findfeedback.SurveyExistCheckPort;
import me.nalab.survey.application.port.out.persistence.findtarget.TargetFindPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SurveyBookmarkReplaceService implements SurveyBookmarkReplaceUseCase {

    private final TargetFindPort targetFindPort;
    private final SurveyExistCheckPort surveyExistCheckPort;

    @Override
    @Transactional
    public SurveyBookmarkDto flipBookmark(Long targetId, Long surveyId) {
        var target = targetFindPort.findTargetById(targetId)
            .orElseThrow(() -> new TargetDoesNotExistException(targetId));

        if (!surveyExistCheckPort.isExistSurveyBySurveyId(surveyId)) {
            throw new SurveyDoesNotExistException(surveyId);
        }

        target.flipBookmark(surveyId);

        System.out.println(">>> nickname " + target.getNickname());

        return SurveyBookmarkDto.builder()
            .surveyId(surveyId)
            .targetId(target.getId())
            .nickname(target.getNickname())
            .job(target.getJob())
            .imageUrl(target.getImageUrl())
            .position(target.getPosition())
            .build();
    }
}

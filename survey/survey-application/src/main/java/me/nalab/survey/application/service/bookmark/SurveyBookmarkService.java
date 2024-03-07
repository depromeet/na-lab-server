package me.nalab.survey.application.service.bookmark;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.exception.SurveyDoesNotExistException;
import me.nalab.survey.application.exception.TargetDoesNotHasSurveyException;
import me.nalab.survey.application.port.in.web.bookmark.SurveyBookmarkUseCase;
import me.nalab.survey.application.port.out.persistence.bookmark.SurveyBookmarkListenPort;
import me.nalab.survey.application.port.out.persistence.bookmark.SurveyBookmarkPort;
import me.nalab.survey.application.port.out.persistence.findfeedback.SurveyExistCheckPort;
import me.nalab.survey.application.port.out.persistence.findtarget.TargetFindPort;
import me.nalab.survey.application.port.out.persistence.findtarget.TargetIdFindPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SurveyBookmarkService implements SurveyBookmarkUseCase {

    private final TargetFindPort targetFindPort;
    private final TargetIdFindPort targetIdFindPort;
    private final SurveyExistCheckPort surveyExistCheckPort;
    private final SurveyBookmarkPort surveyBookmarkPort;
    private final SurveyBookmarkListenPort surveyBookmarkListener;

    @Override
    @Transactional
    public void bookmark(Long targetId, Long surveyId) {
        var target = targetFindPort.getTargetById(targetId);

        if (!surveyExistCheckPort.isExistSurveyBySurveyId(surveyId)) {
            throw new SurveyDoesNotExistException(surveyId);
        }

        var bookmarkSuccess = target.bookmark(surveyId);
        if (bookmarkSuccess) {
            var bookmarkTargetId = targetIdFindPort.findTargetIdBySurveyId(surveyId)
                .orElseThrow(() -> new TargetDoesNotHasSurveyException(surveyId));
            surveyBookmarkListener.increaseBookmarked(bookmarkTargetId);
            surveyBookmarkPort.updateBookmark(target);
        }
    }

    @Override
    @Transactional
    public void cancelBookmark(Long targetId, Long surveyId) {
        var target = targetFindPort.getTargetById(targetId);

        if (!surveyExistCheckPort.isExistSurveyBySurveyId(surveyId)) {
            throw new SurveyDoesNotExistException(surveyId);
        }

        var cancelSuccess = target.cancelBookmark(surveyId);
        if (cancelSuccess) {
            var bookmarkTargetId = targetIdFindPort.findTargetIdBySurveyId(surveyId)
                .orElseThrow(() -> new TargetDoesNotHasSurveyException(surveyId));
            surveyBookmarkListener.decreaseBookmarked(bookmarkTargetId);
            surveyBookmarkPort.updateBookmark(target);
        }
    }
}

package me.nalab.survey.application.service.bookmark;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.common.survey.dto.SurveyBookmarkDto;
import me.nalab.survey.application.port.in.web.bookmark.BookmarkedSurveyFindUseCase;
import me.nalab.survey.application.port.out.persistence.findtarget.TargetFindPort;
import me.nalab.survey.application.port.out.persistence.survey.find.SurveyFindPort;
import me.nalab.survey.domain.target.SurveyBookmark;
import me.nalab.survey.domain.target.Target;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookmarkedSurveyFindService implements BookmarkedSurveyFindUseCase {

    private final TargetFindPort targetFindPort;
    private final SurveyFindPort surveyFindPort;

    @Override
    @Transactional(readOnly = true)
    public List<SurveyBookmarkDto> findBookmarkedSurveysByTargetId(Long targetId, Long lastSurveyId, Integer count) {
        var target = targetFindPort.getTargetById(targetId);
        var bookmarkedTargets = targetFindPort.findAllTargetBySurveyIds(getSurveyIds(target, lastSurveyId, count));

        return bookmarkedTargets.stream()
            .map(bookmarkedTarget -> {
                var surveyId = bookmarkedTarget.getSurveyList().get(0).getId();
                return SurveyBookmarkDto.from(surveyId, bookmarkedTarget);
            })
            .toList();
    }

    private Set<Long> getSurveyIds(Target target, Long lastSurveyId, Integer count) {
        return target.getBookmarkedSurveys().stream()
            .map(SurveyBookmark::surveyId)
            .filter(aLong -> aLong > lastSurveyId)
            .limit(count)
            .collect(Collectors.toSet());
    }
}

package me.nalab.survey.jpa.adaptor.bookmark;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.exception.TargetDoesNotExistException;
import me.nalab.survey.application.port.out.persistence.bookmark.SurveyBookmarkPort;
import me.nalab.survey.domain.target.Target;
import me.nalab.survey.jpa.adaptor.common.mapper.TargetEntityMapper;
import me.nalab.survey.jpa.adaptor.find.repository.TargetFindRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SurveyBookmarkAdaptor implements SurveyBookmarkPort {

    private final TargetFindRepository targetFindRepository;

    @Override
    public void bookmark(Target target) {
        var savedTarget = targetFindRepository.findById(target.getId())
            .orElseThrow(() -> new TargetDoesNotExistException(target.getId()));

        savedTarget.setBookmarkedSurveys(TargetEntityMapper.toSurveyBookmarkEntity(target.getBookmarkedSurveys()));
    }
}

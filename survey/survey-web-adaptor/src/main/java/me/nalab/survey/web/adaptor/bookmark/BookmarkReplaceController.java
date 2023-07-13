package me.nalab.survey.web.adaptor.bookmark;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.nalab.core.authorization.aop.meta.Auth;
import me.nalab.core.authorization.aop.meta.Authorization;
import me.nalab.survey.application.port.in.web.bookmark.BookmarkReplaceUseCase;
import me.nalab.survey.application.service.authorization.FormQuestionFeedbackIdValidatorFactory;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class BookmarkReplaceController {

	private final BookmarkReplaceUseCase bookmarkReplaceUseCase;

	@PatchMapping("/feedbacks/bookmarks")
	@Authorization(factory = FormQuestionFeedbackIdValidatorFactory.class)
	@ResponseStatus(HttpStatus.OK)
	public void replaceBookmark(
		@Auth @RequestParam("form-question-feedback-id") Long formQuestionFeedbackId) {
		bookmarkReplaceUseCase.replaceBookmark(formQuestionFeedbackId);
	}

}

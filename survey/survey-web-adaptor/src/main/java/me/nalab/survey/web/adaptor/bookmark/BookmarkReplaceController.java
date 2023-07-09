package me.nalab.survey.web.adaptor.bookmark;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.port.in.web.bookmark.BookmarkReplaceUseCase;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class BookmarkReplaceController {

	private final BookmarkReplaceUseCase bookmarkReplaceUseCase;

	@PatchMapping("/feedbacks/bookmarks")
	public ResponseEntity<Void> replaceBookmark(
		@RequestParam("form-question-feedback-id") String formQuestionFeedbackId) {
		bookmarkReplaceUseCase.replaceBookmark(Long.valueOf(formQuestionFeedbackId));
		return ResponseEntity.ok().build();
	}

}

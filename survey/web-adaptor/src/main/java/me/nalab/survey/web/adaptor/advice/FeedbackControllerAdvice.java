package me.nalab.survey.web.adaptor.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import me.nalab.survey.application.exception.FeedbackDoesNotExistException;
import me.nalab.survey.domain.exception.IllegalFeedbackException;
import me.nalab.survey.domain.exception.IllegalQuestionFeedbackException;

@Slf4j
@RestControllerAdvice
public class FeedbackControllerAdvice {

	@ExceptionHandler({IllegalFeedbackException.class, IllegalQuestionFeedbackException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ErrorTemplate handleFeedbackException(RuntimeException runtimeException) {
		log.error(runtimeException.getMessage(), runtimeException);
		return ErrorTemplate.of("Illegal feedback request");
	}

	@ExceptionHandler(FeedbackDoesNotExistException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	ErrorTemplate handleFeedbackNotFoundException(FeedbackDoesNotExistException feedbackDoesNotExistException) {
		log.error(feedbackDoesNotExistException.getMessage(), feedbackDoesNotExistException);
		return ErrorTemplate.of("Cannot found any feedback id \"" + feedbackDoesNotExistException.getFeedbackId() + "\"");
	}

}

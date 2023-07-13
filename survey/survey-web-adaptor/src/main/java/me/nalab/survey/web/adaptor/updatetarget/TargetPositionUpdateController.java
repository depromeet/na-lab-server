package me.nalab.survey.web.adaptor.updatetarget;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.nalab.survey.application.port.in.web.target.update.TargetPositionUpdateUseCase;
import me.nalab.survey.web.adaptor.updatetarget.request.TargetPositionUpdateRequest;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class TargetPositionUpdateController {

	private final TargetPositionUpdateUseCase targetPositionUpdateUseCase;

	@PatchMapping("/users")
	public ResponseEntity<Void> updateTargetPosition(@RequestAttribute("logined") Long loginId,
		@Valid @RequestBody TargetPositionUpdateRequest targetPositionUpdateRequest) {
		targetPositionUpdateUseCase.updateTargetPosition(loginId, targetPositionUpdateRequest.getPosition());
		return ResponseEntity.ok().build();
	}

}

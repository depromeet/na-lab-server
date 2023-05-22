package me.nalab.core.exception.handler;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class TestController {

	@PostMapping("/hello")
	void hello(@RequestBody @Validated TestRequest testRequest) {
	}

}

package me.nalab.core.time;

import java.time.ZoneOffset;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
class TestController {

	@GetMapping("/get-time")
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Long> getTime() {
		return Map.of("instant", TimeUtilProxy.toInstant().toEpochMilli(),
			"local", TimeUtilProxy.toLocalDateTime().toEpochSecond(ZoneOffset.UTC));
	}

}

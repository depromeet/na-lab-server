package me.nalab.luffy.api.acceptance.test.user.updatetarget.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class RequestSample {

	private static final ObjectMapper OBJECT_MAPPER;
	public static final String DEFAULT_JSON;

	static {
		OBJECT_MAPPER = new ObjectMapper();
		OBJECT_MAPPER.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
		DEFAULT_JSON = loadDefaultJson();
	}

	private static String loadDefaultJson() {

		try {
			return OBJECT_MAPPER.writeValueAsString(TargetPositionUpdateRequest.builder()
				.position("BE developer")
				.build()
			);
		} catch (JsonProcessingException jpe) {
			throw new IllegalStateException(
				"Cannot start acceptance test fail to load \"DEFAULT_JSON\" " + jpe.getMessage());
		}
	}

}

package me.nalab.core.secure.xss.json;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import me.nalab.core.secure.xss.spi.XssFilter;

@Component
public class JsonXssFilter implements XssFilter {

	private static final String FILTER_NAME = "json";
	private final ObjectMapper objectMapper;

	public JsonXssFilter() {
		objectMapper = new ObjectMapper();
		objectMapper.getFactory().setCharacterEscapes(new XssCharacterEscapes());
		objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
		objectMapper.setVisibility(PropertyAccessor.CREATOR, JsonAutoDetect.Visibility.ANY);
	}

	@Override
	public String getFilterName() {
		return FILTER_NAME;
	}

	@Override
	public <P> P doFilter(Object dirty, Class<P> cast) {
		try {
			return objectMapper.readValue(objectMapper.writeValueAsString(dirty), cast);
		} catch(JsonProcessingException jpe) {
			throw new JsonXssFilterException(jpe.getMessage());
		}
	}

}

package me.nalab.survey.application.service.authorization;

import org.springframework.stereotype.Service;

import me.nalab.core.authorization.api.ParameterReference;
import me.nalab.core.authorization.spi.ParameterExtractor;

@Service
public class LongParameterExtractor implements ParameterExtractor {

	@Override
	public <S> ParameterReference extract(S target) {
		if(target instanceof Long) {
			return ParameterReference.createInstance((Long)target);
		}
		throw new IllegalArgumentException(
			"Cannot create reference cause expected target type is \"Long\" but was \"" + target.getClass() + "\"");
	}

}

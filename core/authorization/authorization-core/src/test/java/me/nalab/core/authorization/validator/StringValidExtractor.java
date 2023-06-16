package me.nalab.core.authorization.validator;

import me.nalab.core.authorization.api.ParameterReference;
import me.nalab.core.authorization.spi.ParameterExtractor;

public class StringValidExtractor implements ParameterExtractor {

	@Override
	public <S> ParameterReference extract(S target) {
		validType(target);
		return ParameterReference.createInstance(target);
	}

	private <S> void validType(S target) {
		if(target instanceof String) {
			return;
		}
		throw new IllegalStateException();
	}

}

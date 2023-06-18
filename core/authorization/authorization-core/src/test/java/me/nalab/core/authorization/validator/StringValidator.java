package me.nalab.core.authorization.validator;

import me.nalab.core.authorization.spi.Validator;

public class StringValidator implements Validator {

	@Override
	public <T, S> boolean valid(T expected, S result) {
		validType(expected, result);
		return expected.equals(result);
	}

	private <T, S> void validType(T expected, S result) {
		if(expected instanceof String && result instanceof String) {
			return;
		}
		throw new IllegalStateException();
	}

}

package me.nalab.core.authorization.aop.validator;

import me.nalab.core.authorization.spi.Validator;

public class LongValidator implements Validator {

	@Override
	public <T, S> boolean valid(T expected, S result) {
		validType(expected, result);
		return expected.equals(result);
	}

	private <T, S> void validType(T expected, S result) {
		if(expected instanceof Long && result instanceof Long) {
			return;
		}
		throw new IllegalStateException();
	}

}

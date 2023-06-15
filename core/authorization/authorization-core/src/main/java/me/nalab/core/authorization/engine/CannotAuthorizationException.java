package me.nalab.core.authorization.engine;

import me.nalab.core.authorization.spi.ValidatorFactory;

public class CannotAuthorizationException extends RuntimeException {

	static final CannotAuthorizationException EXPECTED_NULL
		= new CannotAuthorizationException("Cannot authorization cause expected \"NULL\"");
	static final CannotAuthorizationException TARGET_NULL
		= new CannotAuthorizationException("Cannot authorization cause target \"NULL\"");
	static final CannotAuthorizationException VALIDATOR_NULL
		= new CannotAuthorizationException("Cannot authorization cause validatorFactory \"NULL\"");

	<T, S> CannotAuthorizationException(T expected, S target, ValidatorFactory<?, ?> validatorFactory) {
		super(
			"Cannot authorization cause expected \"" + expected + "\" target \"" + target
				+ "\" validator factory \"" + validatorFactory.getClass() + "\"");
	}

	private CannotAuthorizationException(String message) {
		super(message);
	}

}

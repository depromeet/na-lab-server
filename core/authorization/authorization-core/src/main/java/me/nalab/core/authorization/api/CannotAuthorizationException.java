package me.nalab.core.authorization.api;

import me.nalab.core.authorization.spi.ValidatorFactory;

public class CannotAuthorizationException extends RuntimeException {

	public static final CannotAuthorizationException EXPECTED_NULL
		= new CannotAuthorizationException("Cannot authorization cause expected \"NULL\"");
	public static final CannotAuthorizationException TARGET_NULL
		= new CannotAuthorizationException("Cannot authorization cause target \"NULL\"");
	public static final CannotAuthorizationException VALIDATOR_NULL
		= new CannotAuthorizationException("Cannot authorization cause validatorFactory \"NULL\"");

	public <T, S> CannotAuthorizationException(T expected, S target, ValidatorFactory<?, ?> validatorFactory) {
		super(
			"Cannot authorization cause expected \"" + expected + "\" target \"" + target
				+ "\" validator factory \"" + validatorFactory.getClass() + "\"");
	}

	private CannotAuthorizationException(String message) {
		super(message);
	}

}

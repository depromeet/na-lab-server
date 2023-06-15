package me.nalab.core.authorization.engine;

import me.nalab.core.authorization.api.Authorizer;
import me.nalab.core.authorization.spi.ParameterExtractor;
import me.nalab.core.authorization.spi.Validator;
import me.nalab.core.authorization.spi.ValidatorFactory;

public final class DefaultAuthorizerEngine implements Authorizer {

	public <T, S> void authorization(T expected, S target,
		ValidatorFactory<? extends ParameterExtractor, ? extends Validator> validatorFactory) {

		validParameter(expected, target, validatorFactory);
		Validator validator = validatorFactory.validator();
		ParameterExtractor parameterExtractor = validatorFactory.parameterExtractor();
		ParameterReference parameterReference = parameterExtractor.extract(target);

		boolean result = validator.valid(expected, parameterReference.value(target.getClass()));
		if(!result) {
			throw new CannotAuthorizationException(expected, target, validatorFactory);
		}
	}

	private <T, S> void validParameter(T expected, S target,
		ValidatorFactory<? extends ParameterExtractor, ? extends Validator> validatorFactory) {

		if(expected == null) {
			throw CannotAuthorizationException.EXPECTED_NULL;
		}
		if(target == null) {
			throw CannotAuthorizationException.TARGET_NULL;
		}
		if(validatorFactory == null || validatorFactory.validator() == null
			|| validatorFactory.parameterExtractor() == null) {
			throw CannotAuthorizationException.VALIDATOR_NULL;
		}
	}

}

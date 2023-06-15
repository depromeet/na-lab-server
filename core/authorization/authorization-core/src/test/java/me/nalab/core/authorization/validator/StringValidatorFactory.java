package me.nalab.core.authorization.validator;

import me.nalab.core.authorization.spi.ValidatorFactory;

public class StringValidatorFactory implements ValidatorFactory<StringValidExtractor, StringValidator> {

	@Override
	public StringValidExtractor parameterExtractor() {
		return new StringValidExtractor();
	}

	@Override
	public StringValidator validator() {
		return new StringValidator();
	}

}

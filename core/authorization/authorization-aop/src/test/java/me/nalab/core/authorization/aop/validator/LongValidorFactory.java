package me.nalab.core.authorization.aop.validator;

import me.nalab.core.authorization.spi.ValidatorFactory;

public class LongValidorFactory implements ValidatorFactory<LongValidExtractor, LongValidator> {

	@Override
	public LongValidExtractor parameterExtractor() {
		return new LongValidExtractor();
	}

	@Override
	public LongValidator validator() {
		return new LongValidator();
	}

}

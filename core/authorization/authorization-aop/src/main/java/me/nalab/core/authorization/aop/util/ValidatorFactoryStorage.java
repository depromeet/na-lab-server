package me.nalab.core.authorization.aop.util;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import me.nalab.core.authorization.spi.ParameterExtractor;
import me.nalab.core.authorization.spi.Validator;
import me.nalab.core.authorization.spi.ValidatorFactory;

@Component
@AllArgsConstructor
public class ValidatorFactoryStorage {

	private final ApplicationContext applicationContext;

	public ValidatorFactory<ParameterExtractor, Validator> getFactoryByType(
		Class<? extends ValidatorFactory<ParameterExtractor, Validator>> type) {
		return applicationContext.getBean(type);
	}

}

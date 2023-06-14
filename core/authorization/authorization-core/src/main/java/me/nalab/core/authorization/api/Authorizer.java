package me.nalab.core.authorization.api;

import me.nalab.core.authorization.spi.ParameterExtractor;
import me.nalab.core.authorization.spi.Validator;
import me.nalab.core.authorization.spi.ValidatorFactory;

/**
 * 권한 허가를 진행하는 API 입니다.
 */
public interface Authorizer {

	/**
	 * expected, target, validatorFactory를 인자로 받아, 권한 인증을 진행합니다.
	 *
	 * @param expected 권한 허가로 기대되는 값
	 * @param target 실제로 갖고있는 값
	 * @param validatorFactory 권한 인증을 진행할 객체를 담고있는 Factory
	 *
	 * @param <T> 권한 허가로 기대되는 값의 type
	 * @param <S> 실제로 갖고있는 값의 type
	 */
	<T, S> void authorization(T expected, S target,
		ValidatorFactory<? extends ParameterExtractor, ? extends Validator> validatorFactory);

}

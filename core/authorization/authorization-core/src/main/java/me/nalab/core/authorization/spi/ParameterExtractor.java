package me.nalab.core.authorization.spi;

import me.nalab.core.authorization.api.ParameterReference;

/**
 * 요청받은 인자를 권한 인증에 필요한 ParameterReference 로 Wrapping 하는 SPI 입니다.
 * <br><br>
 * 이 인터페이스의 구현 모듈은 연관된 Validator 가 바인딩된 ValidatorFactory를 함께 재공해야합니다.
 */
public interface ParameterExtractor {

	/**
	 * target을 인자로 받아, ParameterReference 를 반환합니다.
	 *
	 * @param target ParameterReference 로 바인딩 할 인자
	 * @return ParameterReference 인자가 바인딩된 ParameterReference
	 * @param <S> 바인딩할 타겟의 type
	 */
	<S> ParameterReference extract(S target);

}

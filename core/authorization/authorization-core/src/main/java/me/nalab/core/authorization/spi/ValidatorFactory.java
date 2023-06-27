package me.nalab.core.authorization.spi;

/**
 * ParameterExtractor 와 Validator를 연결하는 SPI 입니다.
 * <br> <br>
 * 이 인터페이스의 구현모듈은 ParameterExtractor와 ParameterExtractor를 처리 가능한 Validator를 구현해야합니다.
 *
 * @param <T> ParameterExtractor의 구현체
 * @param <S> Validator의 구현체
 */
public interface ValidatorFactory<T extends ParameterExtractor, S extends Validator> {

	/**
	 * 등록된 parameterExtractor instance를 반환합니다.
	 *
	 * @return parameterExtractor의 instance
	 */
	T parameterExtractor();

	/**
	 * 등록된 validator instance를 반환합니다.
	 *
	 * @return Validator의 instance
	 */
	S validator();

}

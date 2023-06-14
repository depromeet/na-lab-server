package me.nalab.core.authorization.spi;

/**
 * 실제 권한 인증 로직을 담당하는 SPI 입니다.
 *
 * <br></br>
 * 이 인터페이스의 구현 모듈은 연관된 ParameterExtractor 가 바인딩된 ValidatorFactory를 함께 재공해야합니다.
 */
public interface Validator {

	/**
	 * expected와 result 를 인자로 받아, 검증로직을 수행합니다.
	 *
	 * @param expected 권한 허가로 기대되는 값
	 * @param result 실제로 갖고있는 값
	 * @return boolean 성공했다면 true, 실패했다면 false
	 * @param <T> 권한 허가로 기대되는 값의 type
	 * @param <S> 실제로 갖고있는 값의 type
	 */
	<T, S> boolean valid(T expected, S result);

}

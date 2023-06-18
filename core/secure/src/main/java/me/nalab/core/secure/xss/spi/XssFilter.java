package me.nalab.core.secure.xss.spi;

/**
 * 이 인터페이스의 구현체는 파라미터를 전달받아 Xss-safe 한 응답을 반환해야함.
 *
 * @since 0.1
 * @author devxb
 */
public interface XssFilter {

	/**
	 * XssFilter 구현체의 이름을 반환함. <br>
	 * 이 이름은 Xss Filtering 대상을 식별하는데 사용됨. {@link org.stage.xss.core.meta.Xss} <br>
	 *
	 * @return String XssFilter 구현체의 의 이름
	 */
	String getFilterName();

	/**
	 * 파라미터 'dirty' 와 'cast' 를 인자로 받고, <br>
	 * 'dirty' 를 Xss-safe 한 상태로 필터링 한 결과를 'cast' 타입으로 반환함.
	 *
	 * @param dirty Xss-safe 한 상태로 필터링 할 대상.
	 * @param cast Xss-safe 한 객체가 반환될 타입.
	 * @return Xss-safe 한 객체
	 * @param <P> 반환 될 타입
	 */
	<P> P doFilter(Object dirty, Class<P> cast);

}

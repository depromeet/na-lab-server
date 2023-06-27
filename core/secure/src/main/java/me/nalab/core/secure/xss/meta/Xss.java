package me.nalab.core.secure.xss.meta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@link XssFiltering} 어노테이션이 마크되면 메소드의 파라미터에 지정하며 사용함.<br>
 * {@link Xss} 어노테이션이 마크된 파라미터는 메소드 호출 전에 Xss filtering되어서 전달됨.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Xss {

	/**
	 * value() 변수를 지정하면, 파라미터를 필터링할때 사용할 {@link org.stage.xss.core.spi.XssFilter} 를 선택 할 수 있음.<br>
	 * 만약, 비어있다면, 파라미터의 클래스명을 모두 소문자로 변경한 값이 지정됨.
	 *
	 * @return String 이 파라미터를 필터링할때 사용할 {@link org.stage.xss.core.spi.XssFilter} 의 이름
	 */
	String value() default "";

	/**
	 * value() 메소드와 같은 역할을 함.
	 *
	 * @return value() 메소드와 같음.
	 */
	String filterName() default "";

}

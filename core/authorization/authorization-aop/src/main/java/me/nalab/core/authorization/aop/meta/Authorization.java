package me.nalab.core.authorization.aop.meta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import me.nalab.core.authorization.spi.ValidatorFactory;

/**
 * 이 어노테이션이 마크된 메소드는 호출전에, 권한 인증이 진행됩니다.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorization {

	/**
	 * 인증에 사용할 ValidatorFactory 구현체를 등록합니다.
	 *
	 * @see ValidatorFactory
	 *
	 * @return ValidatorFactory 구현체의 class
	 */
	Class<?> factory();

}

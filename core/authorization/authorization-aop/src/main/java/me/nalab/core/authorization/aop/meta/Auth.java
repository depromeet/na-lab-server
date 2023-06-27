package me.nalab.core.authorization.aop.meta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 이 어노테이션이 마크된 파라미터는 권한 인증의 대상이 됩니다. <br> <br>
 *
 * *주의* <br>
 * 하나의 메소드의 파라미터에 @Auth 어노테이션이 여러개 마킹되어 있을경우, 첫번째 파라미터만 권한 인증의 대상이 됩니다.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
}

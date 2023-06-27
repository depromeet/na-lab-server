package me.nalab.core.secure.xss.meta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * XssFiltering 대상 메소드임을 식별하는 용도로 사용됨. <br>
 * {@link XssFiltering} 어노테이션이 마크된 메소드는 메소드의 파라미터중 {@link Xss} 어노테이션이 마크된 파라미터에 대해서 XssFiltering 을 수행함. <br>
 *
 * @author devxb
 * @since 0.1
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface XssFiltering {
}

package me.nalab.core.time;

import java.time.Instant;
import java.time.LocalDateTime;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * TimeUtil을 static으로 사용할 수 있는 클래스 입니다.
 * Domain이나 Entity처럼, Bean으로 주입받기 힘든상황에서 사용하세요
 */
@Component
public class TimeUtilProxy implements ApplicationContextAware {

	private static TimeUtil timeUtil;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		setTimeUtil(applicationContext.getBean(TimeUtil.class));
	}

	private static void setTimeUtil(TimeUtil timeUtil) {
		TimeUtilProxy.timeUtil = timeUtil;
	}

	public static LocalDateTime toLocalDateTime() {
		return timeUtil.toLocalDateTime();
	}

	public static Instant toInstant() {
		return timeUtil.toInstant();
	}

}

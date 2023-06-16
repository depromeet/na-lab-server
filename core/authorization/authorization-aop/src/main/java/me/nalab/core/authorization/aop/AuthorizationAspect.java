package me.nalab.core.authorization.aop;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import me.nalab.core.authorization.aop.meta.Auth;
import me.nalab.core.authorization.aop.meta.Authorization;
import me.nalab.core.authorization.aop.util.ValidatorFactoryStorage;
import me.nalab.core.authorization.api.Authorizer;
import me.nalab.core.authorization.spi.ParameterExtractor;
import me.nalab.core.authorization.spi.Validator;
import me.nalab.core.authorization.spi.ValidatorFactory;

@Aspect
@Component
@RequiredArgsConstructor
public class AuthorizationAspect {

	private final Authorizer authorizer;
	private final ValidatorFactoryStorage validatorFactoryStorage;
	private final HttpServletRequest httpServletRequest;

	@Around("@annotation(me.nalab.core.authorization.aop.meta.Authorization)")
	public Object authorization(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		ValidatorFactory<? extends ParameterExtractor, ? extends Validator> validatorFactory = extractValidatorFactory(
			proceedingJoinPoint);

		Class<?> expectedType = httpServletRequest.getAttribute("logined").getClass();
		int targetIdx = getAuthAnnotatedParameterIdx(getMethod(proceedingJoinPoint));
		Object[] args = proceedingJoinPoint.getArgs();
		Class<?> targetType = args[targetIdx].getClass();

		authorizer.authorization(expectedType.cast(httpServletRequest.getAttribute("logined")),
			targetType.cast(args[targetIdx]), validatorFactory);

		return proceedingJoinPoint.proceed(args);
	}

	@SuppressWarnings("unchecked")
	private ValidatorFactory<ParameterExtractor, Validator> extractValidatorFactory(
		JoinPoint joinPoint) {
		Method targetMethod = getMethod(joinPoint);
		Authorization authorization = getAuthorization(targetMethod);
		return validatorFactoryStorage.getFactoryByType(
			(Class<? extends ValidatorFactory<ParameterExtractor, Validator>>)authorization.factory());
	}

	private Method getMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		Class<?> targetClass = joinPoint.getTarget().getClass();
		try {
			return getDeclaredMethod(methodName, targetClass);
		} catch(NoSuchMethodException nse) {
			return getInheritedMethod(methodName, targetClass);
		}
	}

	private Method getDeclaredMethod(String methodName, Class<?> targetClass) throws NoSuchMethodException {
		Method[] methods = targetClass.getDeclaredMethods();
		return findMethodByName(methods, methodName);
	}

	private Method getInheritedMethod(String methodName, Class<?> targetClass) {
		try {
			Method[] methods = targetClass.getMethods();
			return findMethodByName(methods, methodName);
		} catch(NoSuchMethodException nse) {
			throw new IllegalStateException(
				"Cannot aspect method cause, cannot find method named by \"" + targetClass.getName() + "." + methodName
					+ "()\"");
		}
	}

	private Method findMethodByName(Method[] methods, String methodName) throws NoSuchMethodException {
		for(Method method : methods) {
			if(method.getName().equals(methodName)) {
				return method;
			}
		}
		throw new NoSuchMethodException();
	}

	private Authorization getAuthorization(Method method) {
		Authorization authorization = method.getDeclaredAnnotation(Authorization.class);
		if(authorization == null) {
			authorization = method.getAnnotation(Authorization.class);
		}
		if(authorization == null) {
			throw new IllegalStateException(
				"Cannot aspect method cause, cannot find annotation by \"" + method.getName() + "\""
			);
		}
		return authorization;
	}

	private int getAuthAnnotatedParameterIdx(Method method) {
		Parameter[] parameters = method.getParameters();
		for(int i = 0; i < parameters.length; i++) {
			if(isAuthAnnotatedParameter(parameters[i])) {
				return i;
			}
		}
		throw new IllegalStateException("Cannot find @Auth annotated parameter on \"" + method.getName() + "\"");
	}

	private boolean isAuthAnnotatedParameter(Parameter parameter) {
		Auth auth = parameter.getDeclaredAnnotation(Auth.class);
		if(auth == null) {
			auth = parameter.getAnnotation(Auth.class);
		}
		return auth != null;
	}

}

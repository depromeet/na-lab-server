package me.nalab.core.authorization.engine;

public class WrongReferenceTypeException extends RuntimeException {

	<T, S> WrongReferenceTypeException(Class<T> expectedType, S referencedType) {
		super("Cannot case type expected type \"" + expectedType + "\" referencedType \"" + referencedType.getClass()
			+ "\"");
	}

}

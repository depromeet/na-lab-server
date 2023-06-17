package me.nalab.core.authorization.api;

public class ParameterReference {

	private final Reference<?> reference;

	private <T> ParameterReference(T type) {
		reference = new Reference<>(type);
	}

	public static <T> ParameterReference createInstance(T type) {
		return new ParameterReference(type);
	}

	public <T> T value(Class<T> expectedType) {
		if(reference.isCastable(expectedType)) {
			return expectedType.cast(reference.type);
		}
		throw new WrongReferenceTypeException(expectedType, reference.type);
	}

	private static class Reference<T> {

		private final T type;

		private Reference(T type) {
			this.type = type;
		}

		private <S> boolean isCastable(Class<S> expectedType) {
			return expectedType.equals(type.getClass());
		}

	}

}

package me.nalab.core.secure.xss.exception;

public final class UnknownXssFilterException extends RuntimeException {

	public UnknownXssFilterException(String name) {
		super("Unknown xss filter named \"" + name + "\"");
	}

}

package me.nalab.core.secure.xss;

import me.nalab.core.secure.xss.meta.Xss;
import me.nalab.core.secure.xss.meta.XssFiltering;

public class XssFilterTarget {

	@XssFiltering
	public String filteringString(@Xss("string") String hello) {
		return hello;
	}

	@XssFiltering
	public String filteringTwoParameter(@Xss("string") String hello, @Xss String world) {
		return hello + world;
	}

	@XssFiltering
	public String filteringOneParameter(@Xss String hello, String world) {
		return hello + world;
	}

	@XssFiltering
	public String filterWrongXssAnnotation(@Xss("wrong") String hello) {
		return hello;
	}

}

package me.nalab.core.secure.xss;

import org.springframework.stereotype.Component;

import me.nalab.core.secure.xss.spi.XssFilter;

@Component
public class XssTestFilter implements XssFilter {

	private static final String FILTER_NAME = "string";

	@Override
	public String getFilterName() {
		return FILTER_NAME;
	}

	@Override
	public <P> P doFilter(Object dirty, Class<P> cast) {
		return cast.cast("hello world");
	}

}

package me.nalab.core.secure.xss.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.nalab.core.secure.xss.engine.aop.XssAopEngine;
import me.nalab.core.secure.xss.spi.XssFilter;

@Configuration
public class XssConfig {

	@Bean
	public XssAopEngine xssFilterAop(List<XssFilter> xssFilterList) {
		return new XssAopEngine(xssFilterList);
	}

}

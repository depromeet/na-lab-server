package me.nalab.core.idgenerator.tsid;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.nalab.core.idgenerator.tsid.engine.TsidEngine;

@Configuration
public class TsidConfigurer {

	@Bean("defaultTsidGenerator")
	TsidGenerator defaultTsidGenerator() {
		return new TsidGenerator(TsidEngine.defaultEngine());
	}

	@Bean("threadTsidGenerator")
	TsidGenerator threadTsidGenerator() {
		return new TsidGenerator(TsidEngine.threadEngine());
	}

}

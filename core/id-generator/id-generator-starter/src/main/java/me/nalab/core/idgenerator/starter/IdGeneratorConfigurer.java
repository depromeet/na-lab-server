package me.nalab.core.idgenerator.starter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.nalab.core.idgenerator.idcore.IdGenerator;
import me.nalab.core.idgenerator.tsid.TsidGenerator;
import me.nalab.core.idgenerator.tsid.engine.TsidEngine;

@Configuration
class IdGeneratorConfigurer {

	@Bean
	IdGenerator tsidIdGenerator(){
		return new TsidGenerator(TsidEngine.threadEngine());
	}

}

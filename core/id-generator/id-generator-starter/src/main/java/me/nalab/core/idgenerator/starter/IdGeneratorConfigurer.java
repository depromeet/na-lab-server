package me.nalab.core.idgenerator.starter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.nalab.core.idgenerator.idcore.IdGenerator;
import me.nalab.core.idgenerator.mock.MockIdGenerator;

@Configuration
class IdGeneratorConfigurer {

	@Bean
	IdGenerator randomIdGenerator(){
		return new MockIdGenerator();
	}

}

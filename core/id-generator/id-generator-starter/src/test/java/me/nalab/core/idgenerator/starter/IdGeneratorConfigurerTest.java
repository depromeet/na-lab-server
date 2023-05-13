package me.nalab.core.idgenerator.starter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import me.nalab.core.idgenerator.idcore.IdGenerator;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = IdGeneratorConfigurer.class)
class IdGeneratorConfigurerTest {

	@Autowired
	private IdGenerator idGenerator;

	@Test
	@DisplayName("IdGenerator 등록 유무 성공 테스트")
	void ID_GENERATOR_REGISTERED_SUCCESS(){
		assertNotNull(idGenerator);
	}

}
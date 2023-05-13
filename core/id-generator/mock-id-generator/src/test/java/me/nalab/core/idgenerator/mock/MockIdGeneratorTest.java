package me.nalab.core.idgenerator.mock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import me.nalab.core.idgenerator.idcore.IdGenerator;

class MockIdGeneratorTest {

	private final IdGenerator mockIdGenerator;

	public MockIdGeneratorTest() {
		this.mockIdGenerator = new MockIdGenerator();
	}

	@Test
	@DisplayName("Id 생성 성공 테스트")
	void CREATE_ID_SUCCESS(){
		assertInstanceOf(Long.class, mockIdGenerator.generate());
	}

}

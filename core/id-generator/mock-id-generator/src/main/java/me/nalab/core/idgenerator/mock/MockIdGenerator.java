package me.nalab.core.idgenerator.mock;

import java.security.SecureRandom;

import me.nalab.core.idgenerator.idcore.IdGenerator;

public class MockIdGenerator implements IdGenerator {

	private static final SecureRandom SECURE_RANDOM = new SecureRandom();

	@Override
	public long generate() {
		return SECURE_RANDOM.nextLong();
	}

}

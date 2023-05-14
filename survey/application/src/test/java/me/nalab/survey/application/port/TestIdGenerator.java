package me.nalab.survey.application.port;

import java.security.SecureRandom;

import me.nalab.core.idgenerator.idcore.IdGenerator;

public class TestIdGenerator implements IdGenerator {

	private final static SecureRandom SECURE_RANDOM = new SecureRandom();

	@Override
	public long generate() {
		return SECURE_RANDOM.nextLong();
	}

}

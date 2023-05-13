package me.nalab.core.idgenerator.mock;

import java.util.Random;

import me.nalab.core.idgenerator.idcore.IdGenerator;

public class MockIdGenerator implements IdGenerator {

	private static final Random RANDOM = new Random();

	@Override
	public long generate() {
		return RANDOM.nextLong();
	}

}

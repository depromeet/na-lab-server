package me.nalab.survey.application;

import java.security.SecureRandom;
import java.util.function.Supplier;

import me.nalab.core.idgenerator.idcore.IdGenerator;

public class TestIdGenerator implements IdGenerator {

	private Supplier<Long> idGenerateAlgorithm;

	public TestIdGenerator() {
		idGenerateAlgorithm = new Supplier<>() {
			final SecureRandom secureRandom = new SecureRandom();

			@Override
			public Long get() {
				return secureRandom.nextLong();
			}
		};
	}

	@Override
	public long generate() {
		return idGenerateAlgorithm.get();
	}

	public void setIdGenerateAlgorithm(Supplier<Long> idGenerateAlgorithm) {
		this.idGenerateAlgorithm = idGenerateAlgorithm;
	}

}

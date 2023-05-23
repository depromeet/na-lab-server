package me.nalab.survey.application;

import java.util.function.Supplier;

import me.nalab.core.idgenerator.idcore.IdGenerator;

public class TestIdGenerator implements IdGenerator {

	private Supplier<Long> idGenerateAlgorithm;

	public TestIdGenerator() {
		idGenerateAlgorithm = new Supplier<>() {
			private Long id = 0L;

			@Override
			public Long get() {
				id++;
				return id;
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

package me.nalab.core.idgenerator.tsid;

import java.util.function.Supplier;

import com.github.f4b6a3.tsid.TsidFactory;

import me.nalab.core.idgenerator.idcore.IdGenerator;

public class TsidGenerator implements IdGenerator {

	private final Supplier<TsidFactory> tsidFactorySupplier;

	public TsidGenerator(Supplier<TsidFactory> tsidFactorySupplier) {
		this.tsidFactorySupplier = tsidFactorySupplier;
	}

	@Override
	public long generate() {
		return tsidFactorySupplier.get().create().toLong();
	}

}

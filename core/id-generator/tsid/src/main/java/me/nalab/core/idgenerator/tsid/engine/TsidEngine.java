package me.nalab.core.idgenerator.tsid.engine;

import java.util.HashMap;
import java.util.function.Supplier;

import com.github.f4b6a3.tsid.TsidFactory;

public final class TsidEngine {

	private static final TsidFactory DEFAULT_ENGINE = TsidFactory.newInstance256();
	private static final HashMap<Long, TsidFactory> THREAD_ENGINE = new HashMap<>();
	private static final ThreadEngineSupplier THREAD_ENGINE_SUPPLIER = new ThreadEngineSupplier();

	private TsidEngine() {
		throw new UnsupportedOperationException("Cannot invoke constructor \"TsidEngine()\"");
	}

	public static Supplier<TsidFactory> defaultEngine() {
		return () -> DEFAULT_ENGINE;
	}

	public static Supplier<TsidFactory> threadEngine() {
		return THREAD_ENGINE_SUPPLIER;
	}

	private static final class ThreadEngineSupplier implements Supplier<TsidFactory> {

		@Override
		public TsidFactory get() {
			long threadId = Thread.currentThread().getId() % 256;
			createNewTsidFactoryIfAbsent(threadId);
			return THREAD_ENGINE.get(threadId);
		}

		private void createNewTsidFactoryIfAbsent(long threadId) {
			if(THREAD_ENGINE.containsKey(threadId)) {
				return;
			}
			THREAD_ENGINE.put(threadId, TsidFactory.newInstance256((int)threadId));
		}

	}

}

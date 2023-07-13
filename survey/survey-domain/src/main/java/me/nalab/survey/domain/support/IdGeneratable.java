package me.nalab.survey.domain.support;

import java.util.function.LongSupplier;

public interface IdGeneratable {

	void withId(LongSupplier idSupplier);

}

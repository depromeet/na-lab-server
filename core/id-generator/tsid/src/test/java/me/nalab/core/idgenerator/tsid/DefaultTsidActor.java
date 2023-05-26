package me.nalab.core.idgenerator.tsid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DefaultTsidActor {

	private final TsidGenerator tsidGenerator;

	@Autowired
	public DefaultTsidActor(@Qualifier("defaultTsidGenerator") TsidGenerator tsidGenerator) {
		this.tsidGenerator = tsidGenerator;
	}

	public long act() {
		return tsidGenerator.generate();
	}

}

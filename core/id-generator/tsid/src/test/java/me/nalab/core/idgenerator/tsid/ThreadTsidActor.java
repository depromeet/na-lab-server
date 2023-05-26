package me.nalab.core.idgenerator.tsid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ThreadTsidActor {

	private final TsidGenerator tsidGenerator;

	@Autowired
	public ThreadTsidActor(@Qualifier("threadTsidGenerator") TsidGenerator tsidGenerator) {
		this.tsidGenerator = tsidGenerator;
	}

	public long act() {
		return tsidGenerator.generate();
	}

}

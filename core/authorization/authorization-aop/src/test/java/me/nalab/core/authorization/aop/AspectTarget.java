package me.nalab.core.authorization.aop;

import org.springframework.stereotype.Component;

import me.nalab.core.authorization.aop.meta.Auth;
import me.nalab.core.authorization.aop.meta.Authorization;
import me.nalab.core.authorization.aop.validator.LongValidorFactory;

@Component
class AspectTarget {

	@Authorization(factory = LongValidorFactory.class)
	Long isArrived(@Auth Long id) {
		return id;
	}

	@Authorization(factory = LongValidorFactory.class)
	Long isArrived(@Auth Long id1, @Auth Long id2) {
		return id1;
	}

	@Authorization(factory = LongValidorFactory.class)
	Long isArrivedWithOutAuth(Long id) {
		return id;
	}

}

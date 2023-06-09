module luffy.user.application.main {
	exports me.nalab.user.application.common.dto;
	exports me.nalab.user.application.port.in;
	exports me.nalab.user.application.port.out.persistence;

	requires luffy.user.domain.main;
	requires luffy.survey.domain.main;
	requires luffy.survey.application.main;
	requires luffy.core.id.generator.id.core.main;

	requires lombok;
	requires java.validation;

	requires spring.boot;
	requires spring.boot.autoconfigure;
	requires spring.context;
	requires spring.tx;
	requires spring.beans;
}

module luffy.survey.web.adaptor.main {

	requires luffy.survey.application.main;
	requires luffy.survey.domain.main;
	requires luffy.core.authorization.authorization.aop.main;
	requires luffy.core.secure.main;

	requires lombok;

	requires com.fasterxml.jackson.annotation;

	requires java.validation;

	requires spring.web;
	requires spring.boot;
	requires spring.boot.autoconfigure;
	requires spring.context;
	requires spring.boot.starter;
	requires spring.boot.starter.web;
	requires luffy.core.time.main;

}

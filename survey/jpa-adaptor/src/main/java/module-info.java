module luffy.survey.jpa.adaptor.main {

	requires luffy.core.data.main;
	requires luffy.survey.application.main;
	requires luffy.survey.domain.main;

	requires spring.data.jpa;

	requires lombok;
	requires java.persistence;
	requires spring.context;
	requires spring.boot;
	requires spring.data.commons;

	requires spring.tx;
	requires spring.beans;
}

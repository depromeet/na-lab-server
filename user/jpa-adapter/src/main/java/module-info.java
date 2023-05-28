module luffy.user.jpa.adapter.main {
	requires luffy.core.data.main;
	requires luffy.user.domain.main;
	requires luffy.user.application.main;

	requires spring.data.jpa;

	requires lombok;
	requires java.persistence;
	requires spring.context;
	requires spring.beans;
	requires spring.data.commons;
	requires spring.tx;
}

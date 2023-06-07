module luffy.auth.auth.application.main {

	requires lombok;

	requires spring.core;
	requires spring.beans;
	requires spring.context;
	requires spring.boot;
	requires spring.boot.autoconfigure;

	requires com.auth0.jwt;

	requires luffy.user.application.main;
	requires luffy.user.domain.main;
}
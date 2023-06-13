module luffy.auth.auth.application.main {
	exports me.nalab.auth.application.common.dto;
	exports me.nalab.auth.application.port.in.web;
	exports me.nalab.auth.application.port.in;
	exports me.nalab.auth.application.port.out;

	requires lombok;
	requires com.fasterxml.jackson.annotation;

	requires spring.core;
	requires spring.beans;
	requires spring.context;
	requires spring.boot;
	requires spring.boot.autoconfigure;

	requires com.auth0.jwt;

	requires luffy.user.application.main;
	requires luffy.user.domain.main;
    requires luffy.survey.application.main;
}

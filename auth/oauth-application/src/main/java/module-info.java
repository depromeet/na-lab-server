module luffy.auth.oauth.application.main {
    exports me.nalab.oauth.application;

    requires luffy.auth.auth.application.main;
    requires luffy.user.domain.main;

    requires lombok;

    requires com.fasterxml.jackson.annotation;

    requires spring.context;
    requires spring.beans;
    requires spring.web;
    requires spring.webflux;
    requires spring.core;

}

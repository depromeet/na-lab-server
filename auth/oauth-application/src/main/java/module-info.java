module luffy.auth.oauth.application.main {
    requires luffy.auth.auth.application.main;
    requires luffy.user.domain.main;

    requires lombok;

    requires spring.context;
    requires spring.beans;
    requires spring.web;
    requires spring.webflux;
    requires spring.core;

}

module luffy.auth.auth.web.adaptor.main {
    exports me.nalab.auth.web.adaptor.api;

    requires luffy.auth.auth.application.main;
    requires luffy.auth.oauth.application.main;

    requires lombok;
    requires java.validation;

    requires spring.web;
    requires spring.boot.starter.validation;

    requires com.fasterxml.jackson.annotation;
    requires spring.webmvc;
    requires spring.context;
    requires org.apache.tomcat.embed.core;
    requires spring.beans;

}

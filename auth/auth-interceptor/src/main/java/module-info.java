module luffy.auth.auth.interceptor.main {
	requires spring.webmvc;
	requires spring.context;
	requires spring.beans;
	requires luffy.auth.auth.application.main;
	requires org.apache.tomcat.embed.core;
}

module luffy.auth.mock.main {

	requires spring.context;
	requires spring.webmvc;
	requires org.apache.tomcat.embed.core;
	requires lombok;

	exports me.nalab.auth.mock.api;
	exports me.nalab.auth.mock.config;

}

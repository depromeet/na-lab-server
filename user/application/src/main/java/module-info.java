module luffy.user.application.main {
	exports me.nalab.user.application.port.in;

	requires luffy.user.domain.main;

	requires lombok;

	requires spring.context;
}

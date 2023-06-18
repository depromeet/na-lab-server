module luffy.core.secure.main {
	requires lombok;
	requires org.aspectj.weaver;
	requires spring.context;
	requires com.fasterxml.jackson.databind;
	requires org.apache.commons.text;

	exports me.nalab.core.secure.xss.meta;

}

module luffy.core.data.main {

	exports me.nalab.core.data.common;
	exports me.nalab.core.data.survey;
	exports me.nalab.core.data.target;
	exports me.nalab.core.data.feedback;

	requires lombok;
	requires java.persistence;
	requires spring.data.commons;

}

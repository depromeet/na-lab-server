module luffy.core.data.main {

	exports me.nalab.core.data.common;
	exports me.nalab.core.data.survey;
	exports me.nalab.core.data.target;

	requires lombok;
	requires java.persistence;
	requires java.validation;
	requires spring.data.commons;

}
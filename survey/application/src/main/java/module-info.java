module luffy.survey.application.main {

	exports me.nalab.survey.application.common.survey.dto;
	exports me.nalab.survey.application.port.in.web;
	exports me.nalab.survey.application.port.out.persistence;
	exports me.nalab.survey.application.port.in.web.survey.find;
	exports me.nalab.survey.application.port.in.web.target.find;
	exports me.nalab.survey.application.port.out.persistence.survey.find;
	exports me.nalab.survey.application.port.out.persistence.target.find;
	exports me.nalab.survey.application.port.in.web.getid;
	exports me.nalab.survey.application.exception;
	exports me.nalab.survey.application.port.out.persistence.findid;
	exports me.nalab.survey.application.port.out.persistence.createfeedback;
	exports me.nalab.survey.application.port.in.web.createfeedback;
	exports me.nalab.survey.application.common.feedback.dto;
	exports me.nalab.survey.application.port.out.persistence.feedbacksummary;

	requires luffy.survey.domain.main;
	requires luffy.core.id.generator.id.generator.starter.main;

	requires lombok;

	requires spring.boot;
	requires spring.boot.autoconfigure;
	requires spring.context;
	requires spring.tx;

}

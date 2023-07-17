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
	exports me.nalab.survey.application.port.out.persistence.summaryreviewer;
	exports me.nalab.survey.application.port.in.web.summaryreviewer;
	exports me.nalab.survey.application.port.out.persistence.createfeedback;
	exports me.nalab.survey.application.port.in.web.createfeedback;
	exports me.nalab.survey.application.common.feedback.dto;
	exports me.nalab.survey.application.port.in.web.findfeedback;
	exports me.nalab.survey.application.port.out.persistence.findfeedback;
	exports me.nalab.survey.application.port.out.persistence.feedbacksummary;
	exports me.nalab.survey.application.port.in.web.feedbacksummary;
	exports me.nalab.survey.application.port.out.persistence.feedbackreviewers;
	exports me.nalab.survey.application.service.feedbackreviewers;
	exports me.nalab.survey.application.common.feedback.mapper;
	exports me.nalab.survey.application.port.out.persistence.findspecific;
	exports me.nalab.survey.application.port.in.web.findspecific;
	exports me.nalab.survey.application.port.out.persistence.authorization;
	exports me.nalab.survey.application.common.target.dto;
	exports me.nalab.survey.application.service.authorization;
	exports me.nalab.survey.application.port.out.persistence.bookmark;
	exports me.nalab.survey.application.port.in.web.bookmark;
	exports me.nalab.survey.application.port.out.persistence.target.update;
	exports me.nalab.survey.application.port.in.web.target.update;

	exports me.nalab.survey.application.port.out.persistence.findtarget;
	exports me.nalab.survey.application.port.in.web.findtarget;
	exports me.nalab.survey.application.port.in.web.findfeedback.formtype;
	exports me.nalab.survey.application.port.out.persistence.findfeedback.formtype;
	exports me.nalab.survey.application.port.out.persistence.existsurvey;

	requires luffy.survey.domain.main;
	requires luffy.core.id.generator.id.generator.starter.main;
	requires luffy.core.authorization.authorization.core.main;

	requires lombok;

	requires spring.core;
	requires spring.boot;
	requires spring.boot.autoconfigure;
	requires spring.context;
	requires spring.tx;
	requires spring.beans;

}

module luffy.survey.domain.main {

	exports me.nalab.survey.domain.survey;
	exports me.nalab.survey.domain.target;
	exports me.nalab.survey.domain.survey.valid;

	requires luffy.core.id.generator.id.generator.starter.main;

	requires lombok;

}

package me.nalab.survey.application.port.in.create;

import me.nalab.core.idgenerator.idcore.IdGenerator;
import me.nalab.survey.domain.survey.FormQuestionable;

public interface Questionable<T extends FormQuestionable>{

	T getConcreteQuestion(IdGenerator idGenerator);

}

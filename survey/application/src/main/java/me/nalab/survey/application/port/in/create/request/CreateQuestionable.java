package me.nalab.survey.application.port.in.create.request;

import me.nalab.core.idgenerator.idcore.IdGenerator;
import me.nalab.survey.domain.survey.FormQuestionable;

public interface CreateQuestionable<T extends FormQuestionable> {

	T getConcreteQuestion(IdGenerator idGenerator);

}

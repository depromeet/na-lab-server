package me.nalab.survey.domain.survey.valid;

import java.util.HashSet;
import java.util.List;

import me.nalab.survey.domain.survey.FormQuestionable;
import me.nalab.survey.domain.survey.Survey;

public class SurveyValidator {

	private SurveyValidator() {
		throw new UnsupportedOperationException("Unsupported Operation \"SurveyValidator()\"");
	}

	public static void validSelf(Survey survey) {
		validNoDuplicatedFormOrder(survey.getFormQuestionableList());
	}

	private static void validNoDuplicatedFormOrder(List<FormQuestionable> formQuestionableList) {
		HashSet<Integer> orders = new HashSet<>();
		formQuestionableList.forEach(
			fq -> {
				Integer order = fq.getOrder();
				if(orders.contains(order)) {
					throw new DuplicatedOrderException(order, orders);
				}
				orders.add(order);
			}
		);
	}

}

package me.nalab.survey.domain.survey.valid;

import java.util.HashSet;
import java.util.List;

import me.nalab.survey.domain.survey.Choice;
import me.nalab.survey.domain.survey.ChoiceFormQuestion;

public class ChoiceFormQuestionValidator {

	private ChoiceFormQuestionValidator() {
		throw new UnsupportedOperationException("Unsupported Operation \"ChoiceFormQuestionValidator()\"");
	}

	public static void validSelf(ChoiceFormQuestion choice) {
		validNoDuplicatedChoiceOrder(choice.getChoiceList());
	}

	private static void validNoDuplicatedChoiceOrder(List<Choice> choiceList) {
		HashSet<Integer> orders = new HashSet<>();
		choiceList.forEach(
			c -> {
				Integer order = c.getOrder();
				if(orders.contains(order)) {
					throw new DuplicatedOrderException(order, orders);
				}
				orders.add(order);
			}
		);
	}

}

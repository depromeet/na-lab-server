package me.nalab.survey.domain.survey.valid;

import java.util.HashSet;

public class DuplicatedOrderException extends RuntimeException {

	DuplicatedOrderException(Integer duplicated, HashSet<Integer> orders) {
		super("Duplicated order detected duplicated \"" + duplicated + "\" ordinary \"" + orders.toString() + "\"");
	}

}

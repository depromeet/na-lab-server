package me.nalab.support.jacoco;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class JacocoTest {

	@Test
	void JACOCO_GRADLE_SET_UP(){
		assertNotEquals("hello", getWorld());
	}

	private String getWorld(){
		return "world";
	}

}

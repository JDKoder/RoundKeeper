package com.jdkoder.roundkeeper.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ActorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void createActorTest() {
		Actor testActor = new Actor();
		assertEquals("All Actors should start with their order initialized to 0: ", 0, testActor.getOrder());
	}

}

package com.warriorwebpros.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import com.warriorwebpros.model.Actor;

public class ActorSortingServiceTest {

	ActorSortingService serviceUnderTest;
	
	@Before
	public void setUp() throws Exception {
		serviceUnderTest = new ActorSortingService();
	}

	@Test
	public void testSetOrderByInitiatives() {
		/*Actor a = new Actor();
		Actor b = new Actor();
		Actor c = new Actor();
		
		//Simulate all separate initiatives
		a.setInitiative(14); //Expected order: 1
		b.setInitiative(15); // :0
		c.setInitiative(13); // :2
		*/
		List<Actor> actorList = new ArrayList<Actor>();
		actorList.add(0,makeActorWithInitiative(14));
		actorList.add(1,makeActorWithInitiative(15));
		actorList.add(2,makeActorWithInitiative(13));
		
		actorList = serviceUnderTest.setOrderByInitiative(actorList);
		
		assertEquals("ActorList should be the same size:", 3 ,actorList.size());
		assertEquals("Expect Actor a's order to be 1: ", 1, actorList.get(0).getOrder());
		assertEquals("Expect Actor b's order to be 0: ", 0, actorList.get(1).getOrder());
		assertEquals("Expect Actor c's order to be 2: ", 2, actorList.get(2).getOrder());
	}
	
	@Test
	public void testBreakTiedInitiatives() {
		Actor a = new Actor();
		Actor b = new Actor();
		Actor c = new Actor();
		
		//Simulate an initiative tie
		a.setInitiative(1);
		b.setInitiative(1);
		c.setInitiative(2);
		
		List<Actor> actorList = new ArrayList<Actor>();
		actorList.add(0,a);
		actorList.add(1,b);
		actorList.add(2,c);
		
		Random random = mock(Random.class);
		serviceUnderTest.setRandom(random);
		when(random.nextInt(2)).thenReturn(1);
		
		actorList = serviceUnderTest.breakTiedInitiatives(actorList);
		assertEquals("actorList should be the same size:",3,actorList.size());
		assertEquals("Actor 1 should have initiative 1",1,actorList.get(0).getInitiative());
		assertEquals("Actor 2 should have initiative 2",2,actorList.get(1).getInitiative());
		assertEquals("Actor 3 should have initiative 3",3,actorList.get(2).getInitiative());
	}
	
	@Test
	public void testBreakTieFirstActor(){
		/**given two actors with the same initiative **/
		Actor a1 = new Actor();
		Actor a2 = new Actor();
		a1.setInitiative(1);//0
		a2.setInitiative(1);//1
		//Mock the random generator so we can make this 
		//unit test deterministic.
		Random random = mock(Random.class);
		serviceUnderTest.setRandom(random);
		when(random.nextInt(2)).thenReturn(0);
		List<Actor> actorList;
		/** when we get back the actors in a list **/
		actorList = serviceUnderTest.breakInitiativeTie(a1, a2);
		/** Then verify that the random Number generated updated the initiative of a2 **/
		a1 = actorList.get(0);
		a2 = actorList.get(1);//retrieve actor 2 from list
		verify(random).nextInt(2);
		assertEquals("Actor 1 should now have an initiative Score of 2:", 2, a1.getInitiative());
		assertEquals("Actor 2 should now have an initiative Score of 1:", 1, a2.getInitiative());
	}
	
	@Test
	public void testBreakTieSecondActor(){
		/**given two actors with the same initiative **/
		int actor1I = 1;
		int actor2I = 1;
		//Mock the random generator so we can make this 
		//unit test deterministic.
		Random random = mock(Random.class);
		serviceUnderTest.setRandom(random);
		when(random.nextInt(2)).thenReturn(1);
		List<Actor> actorList;
		/** when we get back the actors in a list **/
		actorList = serviceUnderTest.breakInitiativeTie(
										makeActorWithInitiative(actor1I), 
										makeActorWithInitiative(actor2I));
		/** Then verify that the random Number generated updated the initiative of actor 2 **/
		actor1I = actorList.get(0).getInitiative();
		actor2I = actorList.get(1).getInitiative();//retrieve actor 2 from list
		verify(random).nextInt(2);
		assertEquals("Actor 1 should now have an initiative Score of 1:", 1, actor1I);
		assertEquals("Actor 2 should now have an initiative Score of 2:", 2, actor2I);
	}
	
	@Test
	public void testGetHigherInitiative(){
		int int1 = 400;
		int int2 = 15;
		
		int higherNumber = serviceUnderTest.getHigherInitiative(int1,int2);
		assertEquals("Higher Number is should be returned:",400, higherNumber);
		
		int2 = 415;
		higherNumber = serviceUnderTest.getHigherInitiative(int1,int2);
		assertEquals("Higher Number is should be returned:",415, higherNumber);
		
	}
	
	private Actor makeActorWithInitiative(int i){
		Actor a = new Actor();
		a.setInitiative(i);
		return a;
	}
}

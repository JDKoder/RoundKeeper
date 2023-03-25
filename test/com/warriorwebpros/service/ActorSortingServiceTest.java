package com.warriorwebpros.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.warriorwebpros.model.Actor;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class ActorSortingServiceTest {

	@Mock
	RandomGenerator random;
	@InjectMocks
	ActorSortingService subject;

	@Test
	public void testResetActorOrder(){
		List<Actor> actorList = new ArrayList<Actor>();
		actorList.add(0,makeActorWithInitiative(1));
		actorList.add(1,makeActorWithInitiative(3));
		actorList.get(0).setOrder(2);
		actorList.get(1).setOrder(1);
		actorList = subject.resetActorOrder(actorList);
		assertEquals("Expected order to be 0", 0, actorList.get(0).getOrder());
		assertEquals("Expected order to be 0", 0, actorList.get(1).getOrder());
	}
	
	@Test
	public void testSwapActorsOrder(){
		Actor a1 = makeOrderedActorWithInitiative(initiative(1), order(1));
		Actor a2 = makeOrderedActorWithInitiative(initiative(2), order(0));
		
		subject.swapOrder(a1, a2);
		
		assertEquals("expected a1 order to be equal to 0:", 0, a1.getOrder());
		assertEquals("expected a2 order to be equal to 1:", 1, a2.getOrder());
	}
	
	@Test
	public void testSwapActorsInitiative(){
		Actor a1 = makeOrderedActorWithInitiative(initiative(1), order(1));
		Actor a2 = makeOrderedActorWithInitiative(initiative(2), order(0));
		
		subject.swapInitiative(a1, a2);
		
		assertEquals("expected a1 Initiative to be equal to 2:", 2, a1.getInitiative());
		assertEquals("expected a2 Initiative to be equal to 1:", 1, a2.getInitiative());
	}
	
	@Test
	public void testSwapWithNextActorSwapsActorsInList(){
		Actor a1 = makeNamedOrderedActorWithInitiative(name("a1"), initiative(2), order(0));
		Actor a2 = makeNamedOrderedActorWithInitiative(name("a2"), initiative(1), order(1));
		List<Actor> actorList = new ArrayList<Actor>();
		actorList.add(0,a1);
		actorList.add(1,a2);
		
		List<Actor> returnList = subject.swapWithNextActor(a1, actorList);
		
		assertEquals("Expected returned list to be the same size:", 2, returnList.size());
		assertEquals("Object with name 'a2' should now be first in the list:", name("a2"), returnList.get(0).getName());
		assertEquals("object with name 'a1' should now be second in the list:", name("a1"), returnList.get(1).getName());
	}
	
	@Test
	public void testLastIndexSwapsWithFirstInList(){
		Actor a1 = makeNamedOrderedActorWithInitiative(name("a1"), initiative(3), order(0));
		Actor a2 = makeNamedOrderedActorWithInitiative(name("a2"), initiative(2), order(1));
		Actor a3 = makeNamedOrderedActorWithInitiative(name("a3"), initiative(1), order(2));
		List<Actor> actorList = new ArrayList<Actor>();
		actorList.add(0,a1);
		actorList.add(1,a2);
		actorList.add(2,a3);
		
		List<Actor> returnList = subject.swapWithNextActor(a3, actorList); //attempts to swap the last object in the list
		
		assertEquals("Expected returned list to be the same size:", 3, returnList.size());
		assertEquals("Object with name 'a3' should now be first in the list:", name("a3"), returnList.get(0).getName());
		assertEquals("object with name 'a1' should now be last in the list:", name("a1"), returnList.get(2).getName());
	}
	
	@Test
	public void testSwapActorsUnswappable(){
		Actor a1 = makeNamedOrderedActorWithInitiative(name("a1"), initiative(2), order(0));
		List<Actor> actorList = new ArrayList<Actor>();
		actorList.add(0,a1);
		
		List<Actor> returnList = subject.swapWithNextActor(a1, actorList);
		
		assertEquals("Expected returned list to be the same size:", 1, returnList.size());
	}
	
	@Test
	public void testSetOrderByInitiatives() {
		//Need to set order and Initiative on two actors and simulate passing in an unordered actor
		List<Actor> actorList = new ArrayList<Actor>();
		actorList.add(0,makeOrderedActorWithInitiative(initiative(14), order(1)));
		actorList.add(1,makeOrderedActorWithInitiative(initiative(15), order(0)));
		actorList.add(2,makeActorWithInitiative(initiative(13)));
		
		actorList = subject.setOrderByInitiative(actorList);
		
		assertEquals("ActorList should be the same size:", 3 ,actorList.size());
		assertEquals("Expect Actor a's order to be 1: ", 1, actorList.get(0).getOrder());
		assertEquals("Expect Actor b's order to be 0: ", 0, actorList.get(1).getOrder());
		assertEquals("Expect Actor c's order to be 2: ", 2, actorList.get(2).getOrder());
	}
	
	/**
	 * Test that breaking a tie breaks the tie, and increases all other initiatives
	 * so their sorted order will remain intact.
	 */
	@Test
	public void testBreakTiedInitiatives() {
		//Simulate a tied initiative
		Actor a = makeOrderedActorWithInitiative(initiative(1), order(1));
		Actor b = makeOrderedActorWithInitiative(initiative(2), order(0));
		Actor c = makeActorWithInitiative(1);
		
		List<Actor> actorList = new ArrayList<Actor>();
		actorList.add(0,a);
		actorList.add(1,b);
		actorList.add(2,c);
		when(random.nextInt(2)).thenReturn(1).//Increase c's initiative by 1 to tie with b
								thenReturn(0);//Increase b's initiative so it is greater than c.
		
		actorList = subject.breakTiedInitiatives(actorList);
		assertEquals("actorList should be the same size:",3,actorList.size());
		assertThat("Expected that actor a and b have different initiatives", 
				actorList.get(0).getInitiative(), is(not(actorList.get(1).getInitiative())));
		assertThat("Expected that actor b and c have different initiatives", 
				actorList.get(0).getInitiative(), is(not(actorList.get(2).getInitiative())));
		
		assertEquals("Actor a should have initiative 1",initiative(1),actorList.get(0).getInitiative());
		assertEquals("Actor b should have initiative 3",initiative(3),actorList.get(1).getInitiative());
		assertEquals("Actor c should have initiative 2",initiative(2),actorList.get(2).getInitiative());
	}
	
	@Test
	public void testBreakTieFirstActor(){
		//given two actors with the same initiative
		Actor a1 = makeActorWithInitiative(1);
		Actor a2 = makeActorWithInitiative(1);
		when(random.nextInt(2)).thenReturn(0);
		List<Actor> actorList;
		//when we get back the actors in a list
		actorList = subject.breakInitiativeTie(a1, a2);
		//Then verify that the random Number generated updated the initiative of a2
		a1 = actorList.get(0);
		a2 = actorList.get(1);//retrieve actor 2 from list
		verify(random).nextInt(2);
		assertEquals("Actor 1 should now have an initiative Score of 2:", 2, a1.getInitiative());
		assertEquals("Actor 2 should now have an initiative Score of 1:", 1, a2.getInitiative());
	}
	
	@Test
	public void testBreakTieSecondActor(){
		//given two actors with the same initiative
		Actor a1 = makeActorWithInitiative(1);
		Actor a2 = makeActorWithInitiative(1);
		when(random.nextInt(2)).thenReturn(1);
		List<Actor> actorList;
		// when we get back the actors in a list
		actorList = subject.breakInitiativeTie(a1, a2);
		// Then verify that the random Number generated updated the initiative of actor 2
		int actor1I = actorList.get(0).getInitiative();
		int actor2I = actorList.get(1).getInitiative();//retrieve actor 2 from list
		verify(random).nextInt(2);
		assertEquals("Actor 1 should now have an initiative Score of 1:", 1, actor1I);
		assertEquals("Actor 2 should now have an initiative Score of 2:", 2, actor2I);
	}
	
	@Test
	public void testGetHigherInitiative(){
		int int1 = 400;
		int int2 = 15;
		
		int higherNumber = subject.getHigherInitiative(int1,int2);
		assertEquals("Higher Number is should be returned:",400, higherNumber);
		
		int2 = 415;
		higherNumber = subject.getHigherInitiative(int1,int2);
		assertEquals("Higher Number is should be returned:",415, higherNumber);
	}
	
	@Test
	public void testDefaultOrderActorListOrdersObjectsCorrectly(){
		List<Actor> actorList = new ArrayList<Actor>();
		actorList.add(0,makeActorWithInitiative(3));
		actorList.add(1,makeActorWithInitiative(60));
		actorList.add(2,makeActorWithInitiative(20));
		
		actorList = subject.orderActorList(actorList);
		assertEquals("Expected Actor with initiative 60 to be first in the array.",60, actorList.get(0).getInitiative());
		assertEquals("Expected Actor with initiative 20 to be second in the array.",20, actorList.get(1).getInitiative());
		assertEquals("Expected Actor with initiative 60 to be first in the array.",3, actorList.get(2).getInitiative());
	}
	
	private Actor makeActorWithInitiative(int i){
		Actor a = new Actor();
		a.setInitiative(i);
		return a;
	}
	
	private Actor makeOrderedActorWithInitiative(int initiative, int order){
		Actor a = makeActorWithInitiative(initiative);
		a.setOrder(order);
		return a;
	}
	
	private Actor makeNamedOrderedActorWithInitiative(String name, int initiative, int order) {
		Actor a = makeOrderedActorWithInitiative(initiative, order);
		a.setName(name);
		return a;
	}
	
	private int order(int i){
		return i;
	}
	
	private int initiative(int i){
		return i;
	}
	
	private String name(String name){
		return name;
	}
}

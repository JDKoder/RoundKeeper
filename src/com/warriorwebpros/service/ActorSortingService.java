package com.warriorwebpros.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.warriorwebpros.model.Actor;

public class ActorSortingService {
	
	private Random random;

	/**
	 * Set the Order on all Actor objects in the list based on their initiative scores.
	 * @param actorList
	 * @return
	 */
	public List<Actor> setOrderByInitiative(List<Actor> actorList) {
		//Iterator starts with last in order.
		int i_order = actorList.size() - 1;
		resetActorOrder(actorList);
		while(true){
			Actor sortedActor = actorList.get(i_order);
			actorList.remove(i_order);
			for(Actor actor : actorList){
				if(sortedActor.getInitiative() < actor.getInitiative()){
					sortedActor.setOrder(sortedActor.getOrder() + 1);
				}
			}
			//Set the actor back in the same place in the list we got it.
			actorList.add(i_order, sortedActor);
			i_order -= 1;//update iterator
			if(i_order == -1){
				break;
			}
		}
		return actorList;
	}
	
	/**
	 * Changes the values of each actor's order property to the default starting
	 * value.
	 * @param actorList
	 * @return actorList
	 */
	protected List<Actor> resetActorOrder(List<Actor> actorList){
		for(int i=0; i < actorList.size();i++){
			actorList.get(i).setOrder(0);
		}
		return actorList;
	}

	/**
	 * This class iterates through all of the Actors and mutates the initiatives
	 * to be unique.  
	 * This is a required step before calling setOrderByInitiative
	 * to receive their order.
	 * @param List<Actor> actorList
	 * @return List<Actor> actorList
	 */
	public List<Actor> breakTiedInitiatives(List<Actor> actorList) {
		for(Actor actor1 : actorList) {
			int index1 = actorList.indexOf(actor1);
			for(Actor actor2 : actorList){
				int index2 = actorList.indexOf(actor2);
				if(index1 == index2){
					continue;
				}
				List<Actor> uniqueInitiatives;
				if(isSameInitiative(actor1, actor2)) {
					uniqueInitiatives = breakInitiativeTie(actor1, actor2);				
					actorList.set(index1,uniqueInitiatives.get(0));
					actorList.set(index2,uniqueInitiatives.get(1));
					actorList = 
							increaseHigherInitiativeThanActor(actorList,
															  getActorWithHigherInitiative(
																	  actorList.get(index1), 
																	  actorList.get(index2)));
				} else {
					continue;
				}
			}
		}
		return actorList;
	}
	
	/**
	 * Compares two actor's by their initiative scores.  Whichever actor
	 * has a higher score is one returned.
	 * @param actor1
	 * @param actor2
	 * @return
	 */
	private Actor getActorWithHigherInitiative(Actor a, Actor b){
		Actor returnActor;
		int higherInitiative = getHigherInitiative(a.getInitiative(),b.getInitiative());
		if(a.getInitiative() == higherInitiative){
			returnActor = a;
		} else {
			returnActor = b;
		}
		return returnActor;
	}
	
	private List<Actor> increaseHigherInitiativeThanActor(List<Actor> list, Actor actorToBeat){
		for(int i=0; i<list.size(); i++){
			if(list.get(i).getInitiative() >= actorToBeat.getInitiative() &&
			   list.get(i) != actorToBeat){
				   list.get(i).setInitiative(list.get(i).getInitiative() +1);
			}
		}
		return list;
	}
	/**
	 * Based on the unique order property of each Actor type in the list, 
	 * this function orders the List.
	 * 
	 * @param actorList
	 * @return sorted actorList
	 */
	public List<Actor> orderActorList(List<Actor> actorList){
		actorList = breakTiedInitiatives(actorList);
		actorList = setOrderByInitiative(actorList);
		Collections.sort(actorList, new ActorComparator());
		return actorList;
	}
	
	public class ActorComparator implements Comparator<Actor> {
		@Override
		public int compare(Actor a1, Actor a2) {
			return a1.getOrder() - a2.getOrder();
		}
	}
	
	public List<Actor> breakInitiativeTie(Actor a1, Actor a2){
		if(random == null){
			random = new Random();
		}
		List<Actor> returnList = new ArrayList<Actor>();
		if(random.nextInt(2)+1 == 1){
			a1.setInitiative(a1.getInitiative()+1);
		} else {
			a2.setInitiative(a2.getInitiative()+1);
		}
		returnList.add(0,a1);
		returnList.add(1,a2);
		return returnList;
	}
	
	public Boolean isSameInitiative(Actor a1, Actor a2){
		Boolean b = false;
		if(a1.getInitiative() == a2.getInitiative()){
			b = true;
		}
		return b;
	}
	
	/**
	 * Returns the higher of two passed in actor's initiative scores.
	 * @return
	 */
	public int getHigherInitiative(int a, int b){
		int returnValue = 0;
		if(a > b) {
			returnValue = a;
		} else {
			returnValue = b;
		}
		return returnValue;
	}
	

	public void setRandom(Random random) {
		this.random = random;
	}

	/**
	* If the array size is large enough, swap the actor's orderDependentVariables.
	* 
	* @param actorIndex (The index the actor is located in the list)
	* @param actorList
	*/
	public List<Actor> swapWithNextActor(Actor delayingActor, List<Actor> masterList) {
		if(canSwap(masterList)){ //Don't swap anything if there isn't anything to swap
			int delayingActorIndex = masterList.indexOf(delayingActor);
			Actor delayedActor = masterList.get(delayingActorIndex);
			int nextActorIndex = getNextActorIndex(delayingActorIndex, masterList);
			Actor nextActor = masterList.get(nextActorIndex);
			swapOrderDependentValues(delayedActor, nextActor);
			masterList.set(delayingActorIndex, delayedActor);
			masterList.set(nextActorIndex, nextActor);
			Collections.sort(masterList, new ActorComparator());
		}
		return masterList;
	}
	
	public void swapOrderDependentValues(Actor a1, Actor a2){
		swapOrder(a1, a2);
		swapInitiative(a1, a2);
	}
	
	public void swapOrder(Actor a1, Actor a2) {
		Actor temp = new Actor();
		temp.setOrder(a1.getOrder());
		a1.setOrder(a2.getOrder());
		a2.setOrder(temp.getOrder());
	}
	
	public void swapInitiative(Actor a1, Actor a2) {
		Actor temp = new Actor();
		temp.setInitiative(a1.getInitiative());
		a1.setInitiative(a2.getInitiative());
		a2.setInitiative(temp.getInitiative());
	}
	
	private int getNextActorIndex(int lastIndex, List<Actor>actorList){
		int nextIndex = lastIndex + 1;
		if(nextIndex >= actorList.size()){
			nextIndex = 0;
		}
		return nextIndex;
	}
	
	private boolean canSwap(List<?> list){
		return list.size() > 1;
	}
}

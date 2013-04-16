package com.warriorwebpros.service;

import java.util.List;

import com.warriorwebpros.listeners.ActorListChangedListener;
import com.warriorwebpros.model.Actor;

/**
 * This is the notification service that will update 
 * all of the listeners with the new actor list, when
 * the list is updated
 * @author KriegerJ
 *
 */
public class ActorDataService {
	private List<Actor> masterList;
	private List<ActorListChangedListener> listeners;

	private ActorSortingService sortingService;
	
	public ActorDataService(List<Actor> actorList, ActorSortingService sortingService){
		masterList = actorList;
		this.sortingService = sortingService;
	}
	
	public void addListener(ActorListChangedListener listener){
		listeners.add(listener);
	}
	
	public void removeListener(ActorListChangedListener listener){
		listeners.remove(listener);
	}
	
	public List<Actor> getMasterList(){
		return masterList;
	}
	/**
	 * Notify the listeners that an actor has been added to the masterList
	 * @param actor
	 */
	public void addActor(Actor actor){
		masterList.add(actor);
		sortingService.orderActorList(masterList);
		notifyListeners();
	}
	
	public void delayActorsTurn(Actor actor){
		masterList = sortingService.swapWithNextActor(actor, masterList);
	}
	
	public void notifyListeners(){
		for(ActorListChangedListener listener : listeners){
			listener.handleActorListChanged(null, masterList);
		}
	}
	
	public void setListeners(List<ActorListChangedListener> listeners) {
		this.listeners = listeners;
	}
	
	//remove actor
	//updateOrder(Boolean descending);
	
}

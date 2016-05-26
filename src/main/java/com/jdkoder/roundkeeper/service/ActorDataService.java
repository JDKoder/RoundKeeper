package com.jdkoder.roundkeeper.service;

import com.jdkoder.roundkeeper.listeners.ActorListChangedListener;
import com.jdkoder.roundkeeper.model.Actor;

import java.util.List;

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
	private Actor selectedActor;

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
	
	public void removeActor(){
		masterList.remove(selectedActor);
		if(masterList.size() > 0){
			sortingService.orderActorList(masterList);
		}
		notifyListeners();
	}
	
	public void delayActorsTurn(){
		masterList = sortingService.swapWithNextActor(selectedActor, masterList);
		notifyListeners();
	}
	
	
	public void notifyListeners(){
		for(ActorListChangedListener listener : listeners){
			listener.handleActorListChanged(null, masterList);
		}
	}
	
	public void setListeners(List<ActorListChangedListener> listeners) {
		this.listeners = listeners;
	}
	
	public void setSelectedActor(Actor selectedActor) {
		this.selectedActor = selectedActor;
	}
}

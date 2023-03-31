package com.warriorwebpros.service;

import static com.warriorwebpros.binders.ActorDataModule.ActorList;
import static com.warriorwebpros.binders.ActorDataModule.ActorListeners;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.warriorwebpros.listeners.ActorListChangedListener;
import com.warriorwebpros.model.Actor;

/**
 * This is the notification service that will update 
 * all of the listeners with the new actor list, when
 * the list is updated
 * @author KriegerJ
 *
 */
//TODO: This should not be a singleton, rather it should have a reference to a singleton data source
@Singleton
public class ActorDataService {
	//TODO: refactor to be accessible through data source facade
	private List<Actor> masterList;
	//TODO: refactor to be accessible through data source facade
	private List<ActorListChangedListener> listeners;
	private Actor selectedActor;

	private ActorSortingService sortingService;
	
	@Inject
	public ActorDataService(@ActorList List<Actor> actorList, @ActorListeners List<ActorListChangedListener> listeners, ActorSortingService sortingService){
		this.masterList = actorList;
		this.listeners = listeners;
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

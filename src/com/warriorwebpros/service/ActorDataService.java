package com.warriorwebpros.service;

import static com.warriorwebpros.binders.ActorDataModule.ActorList;
import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.warriorwebpros.model.Actor;

/**
 * This is a simple service that adds and removes and swaps actors given the list of actors, persists them
 * to the data source, and returns a new copy of the updated list.
 * the list is updated
 * @author KriegerJ
 *
 */
//TODO: This should not be a singleton, rather it should have a reference to a singleton data provider
@Singleton
public class ActorDataService {
	//TODO: refactor to be accessible through data source facade
	private List<Actor> masterList;
	private final ActorSortingService sortingService;
	
	@Inject
	public ActorDataService(@ActorList List<Actor> actorList, ActorSortingService sortingService){
		this.masterList = actorList;
		this.sortingService = sortingService;
	}

	public List<Actor> addActorAt(Actor actor){
		masterList.add(actor);
		sortingService.orderActorList(masterList);
		return new ArrayList<>(masterList);
	}

	public Actor getActorAt(int index) {
		Actor actor = null;
		if(!masterList.isEmpty() && index > -1 && index < masterList.size()) {
			actor = masterList.get(index);
		}
		return actor;
	}

	public List<Actor> removeActorAt(int index){
		Actor actor = getActorAt(index);
		if(actor != null) {
			removeActor(actor);
		}
		return new ArrayList<>(masterList);
	}

	public List<Actor> delayActorAt(int index){
		Actor actor = getActorAt(index);
		if(actor != null) {
			masterList = sortingService.swapWithNextActor(masterList.get(index), masterList);
		}
		return new ArrayList<>(masterList);
	}

	protected void removeActor(Actor actor) {
		masterList.remove(actor);
		if(masterList.size() > 0) {
			sortingService.orderActorList(masterList);
		}
	}

}

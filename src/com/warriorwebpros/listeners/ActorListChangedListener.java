package com.warriorwebpros.listeners;

import java.util.EventListener;
import java.util.EventObject;
import java.util.List;

import com.warriorwebpros.model.Actor;

public interface ActorListChangedListener extends EventListener{
	public void handleActorListChanged(EventObject event, List<Actor> list);
}

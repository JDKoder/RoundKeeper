package com.jdkoder.roundkeeper.listeners;

import com.jdkoder.roundkeeper.model.Actor;

import java.util.EventListener;
import java.util.EventObject;
import java.util.List;

public interface ActorListChangedListener extends EventListener{
	public void handleActorListChanged(EventObject event, List<Actor> list);
}

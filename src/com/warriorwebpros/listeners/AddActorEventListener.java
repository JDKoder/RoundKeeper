package com.warriorwebpros.listeners;

import java.util.EventObject;

import com.warriorwebpros.model.Actor;

public interface AddActorEventListener{
	public void handleAddNewActorEvent(EventObject event, Actor actor);
}

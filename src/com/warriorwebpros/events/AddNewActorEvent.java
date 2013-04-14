package com.warriorwebpros.events;

import java.util.EventObject;

import com.warriorwebpros.model.Actor;

public class AddNewActorEvent extends EventObject {
	private static final long serialVersionUID = 8863934554484691722L;

	private Actor _actor;

	public AddNewActorEvent(Object source, Actor actor) {
		super(source);
		_actor = actor;
	}

	public Actor getActor(){
		return _actor;
	}
}

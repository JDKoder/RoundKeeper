package com.warriorwebpros.model;

public class Actor {

	private int initiative;
	private int order;
	private ActorType type;
	
	public ActorType getType() {
		return type;
	}

	public void setType(ActorType type) {
		this.type = type;
	}

	public Actor(){
		order = 0;
	}
	
	public void setInitiative(int value){
		initiative = value;
	}
	
	public int getInitiative(){
		return initiative;
	}
	
	public void setOrder(int value){
		order = value;
	}
	
	public int getOrder(){
		return order;
	}
}

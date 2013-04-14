package com.warriorwebpros.model;

public class Actor {

	private String name;
	private int initiative;
	private int order;
	private ActorType type;
	
	public Actor(String name, int initiative){
		this.name = name;
		this.initiative = initiative;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
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

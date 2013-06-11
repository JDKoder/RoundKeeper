package com.warriorwebpros.colors;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;

public enum RoundKeeperColorConstants{
	TITLE_FONT(255,255,255),
	GROUP_BACKGROUND(200,200,200),
	LABEL_TEXT(250,250,250);

	Color color;
	int red;
	int green;
	int blue;
	
	RoundKeeperColorConstants(int red, int green, int blue){
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	/**
	 * returns the swt color based on the rgb value of the specified
	 * constant.
	 * @param device
	 * @return
	 */
	public Color getColor(Device device) {
		final String METHOD_NAME = "getColor";
		try{
			color = new Color(device, this.red, this.green, this.blue);
		} catch (NullPointerException e){
			System.out.println("Sorry, the passed in device was null. " + 
							   "Please initialize device before calling "+ 
							   	METHOD_NAME + ". " +
							    e.getStackTrace());
		}
		return color;
	}
	
	public int getRed(){
		return red;
	}
	public int getGreen(){
		return green;
	}
	public int getBlue(){
		return blue;
	}
}
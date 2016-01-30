package com;

public class CheckinBaggage extends Baggage {

	private String name;
	private float weight;
	
	
	/**
	 * @param name
	 * @param weight
	 */
	public CheckinBaggage(String name, float weight) {
		super();
		this.name = name;
		this.weight = weight;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
}

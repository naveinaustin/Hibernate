package com;

public class Airport {

	private int id;
	private String name;
	private String city;
	
	
	/**
	 * @param name
	 * @param city
	 */
	public Airport(String name, String city) {
		this.name = name;
		this.city = city;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

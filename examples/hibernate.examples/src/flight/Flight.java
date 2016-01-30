package flight;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author BVR
 * 
 * Class that represents a particular flight instance
 */
public class Flight implements Serializable {

	private String flightNo;

	private String airlines;

	private String from;

	private String to;

	private Set stewards = new HashSet();
	
	private Map passengers = new HashMap();

	public Map getPassengers() {
		return passengers;
	}
	public void setPassengers(Map passengers) {
		this.passengers = passengers;
	}
	public Set getStewards() {
		return stewards;
	}

	public void setStewards(Set stw) {
		stewards = stw;
	}

	public String getAirlines() {
		return airlines;
	}
	public String getFlightNo() {
		return flightNo;
	}
	public String getFrom() {
		return from;
	}
	public String getTo() {
		return to;
	}
	public void setAirlines(String airlines) {
		this.airlines = airlines;
	}
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String toString() {
		return getAirlines() + ":" + getFlightNo() + " from " + getFrom();
	}

}

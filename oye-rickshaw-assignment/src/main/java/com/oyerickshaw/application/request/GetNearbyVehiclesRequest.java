package com.oyerickshaw.application.request;

import java.io.Serializable;

import com.oyerickshaw.application.dto.Location;

public class GetNearbyVehiclesRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2971596560377055399L;
	
	private Location location;

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "GetNearbyVehiclesRequest [location=" + location + "]";
	}
}

package com.oyerickshaw.application.response;

import java.util.ArrayList;
import java.util.List;

import com.oyerickshaw.application.dto.Vehicle;

public class NearbyVehiclesResponse extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1293483321982481640L;
	
	public NearbyVehiclesResponse(String message, boolean status, int code) {
		super.setMessage(message);
		super.setCode(code);
		super.setStatus(status);
	}

	List<Vehicle> vehicles = new ArrayList<>();

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
}

package com.oyerickshaw.application.request;

import java.io.Serializable;

import com.oyerickshaw.application.dto.Location;

public class LocationUpdateRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8541470332759510209L;
	
	private String vehicleNo;
	
	private String vehicleType;
	
	private String ownerName;
	
	private Location location;
	
	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "LocationUpdateRequest [vehicleNo=" + vehicleNo + ", vehicleType=" + vehicleType + ", ownerName="
				+ ownerName + ", location=" + location + "]";
	}
}

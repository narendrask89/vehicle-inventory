package com.vehicle.mgmnt.entity;

public enum VehicleTypeEnum {

	CAR("car"), TRUCK("truck"), AIRPLANE("airplane"), AMPHIBIAN("amphibian"), BOAT("boat");

	private String vehicleType;

	private VehicleTypeEnum(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getVehicleType() {
		return vehicleType;
	}
	
}

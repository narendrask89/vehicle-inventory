package com.vehicle.mgmnt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Vehicle extends AbstractEntity {

	@Column(name = "MODEL_ID", nullable = false, unique = true)
	private String modelId;
	@Column(name = "MAKE_YEAR", nullable = false)
	private int year;
	@Column(name = "MAKE", nullable = false)
	private String make;
	@Column(name = "MODEL", nullable = false)
	private String model;
	@Column(name = "COLOR", nullable = false)
	private String color;
	@Column
	private int mileage;

	@Column(name = "VEHICLE_TYPE")
	@Enumerated(EnumType.STRING)
	private VehicleTypeEnum vehicleType;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public VehicleTypeEnum getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleTypeEnum vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

}

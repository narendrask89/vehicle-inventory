package com.vehicle.mgmnt.service;

import java.util.List;

import com.vehicle.mgmnt.entity.Vehicle;

public interface VehicleInventoryService {

	List<Vehicle> listAllVehicle();

	Vehicle findById(Long id);

	Vehicle findByModel(String model);

	void save(Vehicle vehicle);

	void saveAndFlush(Vehicle currentVehicle);

	void delete(Long id);
}

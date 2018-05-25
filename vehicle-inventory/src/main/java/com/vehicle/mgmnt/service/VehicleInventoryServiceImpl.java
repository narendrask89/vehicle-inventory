package com.vehicle.mgmnt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehicle.mgmnt.entity.Vehicle;
import com.vehicle.mgmnt.repository.VehicleInventoryRepository;

@Service
public class VehicleInventoryServiceImpl implements VehicleInventoryService {

	private VehicleInventoryRepository vehicleInventoryRepository;

	@Autowired
	public VehicleInventoryServiceImpl(VehicleInventoryRepository vehicleInventoryRepository) {
		this.vehicleInventoryRepository = vehicleInventoryRepository;
	}

	@Override
	public List<Vehicle> listAllVehicle() {
		return (List<Vehicle>) vehicleInventoryRepository.findAll();
	}

	/**
	 *
	 * @param id
	 */
	public void delete(Long id) {
		vehicleInventoryRepository.delete(id);
	}

	
	public long count() {
		return vehicleInventoryRepository.count();
	}

	@Override
	public Vehicle findByModelId(String model) {
		return vehicleInventoryRepository.findByModelId(model);
	}

	@Override
	public void save(Vehicle vehicle) {
		vehicleInventoryRepository.save(vehicle);
		
	}

	@Override
	public void saveAndFlush(Vehicle currentVehicle) {
		vehicleInventoryRepository.save(currentVehicle);
		
	}

	@Override
	public Vehicle findById(Long id) {
		return vehicleInventoryRepository.findOne(id);
	}

}

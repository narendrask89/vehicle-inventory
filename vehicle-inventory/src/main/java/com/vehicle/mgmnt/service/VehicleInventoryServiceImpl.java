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
		return null;
	}

	/**
	 *
	 * @param entity
	 * @return
	 */
	public Vehicle create(Vehicle entity) {
		return vehicleInventoryRepository.save(entity);
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public Vehicle read(Long id) {
		return vehicleInventoryRepository.findOne(id);
	}

	/**
	 *
	 * @param entity
	 */
	public void update(Vehicle entity) {
		vehicleInventoryRepository.save(entity);
	}

	/**
	 *
	 * @param id
	 */
	public void delete(Long id) {
		vehicleInventoryRepository.delete(id);
	}

	/**
	 *
	 * @param filter
	 * @return
	 */
	public long count() {
		return vehicleInventoryRepository.count();
	}

	@Override
	public Vehicle findById(Long id) {
		// TODO Auto-generated method stub
		return vehicleInventoryRepository.findOne(id);
	}

	@Override
	public Vehicle findByModel(String model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Vehicle vehicle) {
		// TODO Auto-generated method stub
		vehicleInventoryRepository.save(vehicle);
		
	}

	@Override
	public void saveAndFlush(Vehicle currentVehicle) {
		// TODO Auto-generated method stub
		vehicleInventoryRepository.save(currentVehicle);
		
	}

}

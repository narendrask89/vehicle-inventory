package com.vehicle.mgmnt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vehicle.mgmnt.entity.Vehicle;

@Repository
public interface VehicleInventoryRepository extends CrudRepository<Vehicle, Long> {

	public Vehicle findByModelId(String modelId);
}

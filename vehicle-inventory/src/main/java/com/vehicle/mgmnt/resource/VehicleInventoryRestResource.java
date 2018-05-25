package com.vehicle.mgmnt.resource;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vehicle.mgmnt.entity.Vehicle;
import com.vehicle.mgmnt.exception.CustomErrorType;
import com.vehicle.mgmnt.service.VehicleInventoryService;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleInventoryRestResource {

	public static final Logger logger = LoggerFactory.getLogger(VehicleInventoryRestResource.class);

	private VehicleInventoryService vehicleInventoryService;

	@Autowired
	public VehicleInventoryRestResource(VehicleInventoryService vehicleInventoryService) {
		this.vehicleInventoryService = vehicleInventoryService;
	}

	@GetMapping("/all")
	public ResponseEntity<List<Vehicle>> listAllVehicle() {
		logger.info("Fetching all vehicles");
		List<Vehicle> vehicles = vehicleInventoryService.listAllVehicle();
		if (CollectionUtils.isEmpty(vehicles)) {
			return new ResponseEntity<List<Vehicle>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Vehicle>>(vehicles, HttpStatus.OK);
	}

	@GetMapping("/{model}")
	public ResponseEntity<Vehicle> getVehicleByModel(@PathVariable("model") final String model) {
		logger.info("Fetching Vehicle with model {}", model);
		Vehicle vehicle = vehicleInventoryService.findByModelId(model);
		if (vehicle == null) {
			logger.error("Vehicle with model {} not found.", model);
			return new ResponseEntity<Vehicle>(new CustomErrorType("Vehicle with model " + model + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Vehicle>(vehicle, HttpStatus.OK);
	}

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vehicle> createVehicle(@Valid @RequestBody final Vehicle vehicle) {
		logger.info("Creating Vehicle : {}", vehicle);
		if (vehicleInventoryService.findByModelId(vehicle.getModelId()) != null) {
			logger.error("Unable to create. A Vehicle with Model {} already exist", vehicle.getModelId());
			return new ResponseEntity<Vehicle>(new CustomErrorType(
					"Unable to create new Vehicle. A Vehicle with Model " + vehicle.getModelId() + " already exist."),
					HttpStatus.CONFLICT);
		}
		vehicleInventoryService.save(vehicle);
		return new ResponseEntity<Vehicle>(vehicle, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{modelId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vehicle> updateVehicle(@PathVariable("modelId") final String modelId, @RequestBody Vehicle vehicle) {
		logger.info("Updating vehicle with model Id {}", modelId);
		Vehicle currentVehicle = vehicleInventoryService.findByModelId(modelId);
		if (currentVehicle == null) {
			logger.error("Unable to update. Vehicle with model Id {} not found.", modelId);
			return new ResponseEntity<Vehicle>(
					new CustomErrorType("Unable to upate. Vehicle with model Id " + modelId + " not found."),
					HttpStatus.NOT_FOUND);
		}
		currentVehicle.setColor(vehicle.getColor());
		currentVehicle.setMake(vehicle.getMake());
		currentVehicle.setVehicleType(vehicle.getVehicleType());
		currentVehicle.setModel(vehicle.getModel());
		currentVehicle.setYear(vehicle.getYear());
		currentVehicle.setMileage(vehicle.getMileage());
		vehicleInventoryService.saveAndFlush(currentVehicle);
		return new ResponseEntity<Vehicle>(currentVehicle, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Vehicle> deleteVehicle(@PathVariable("id") final Long id) {
		logger.info("Deleting Vehicle with id {}", id);
		Vehicle vehicle = vehicleInventoryService.findById(id);
		if (vehicle == null) {
			logger.error("Unable to delete. Vehhicle with id {} not found.", id);
			return new ResponseEntity<Vehicle>(
					new CustomErrorType("Unable to delete. Vehicle with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		vehicleInventoryService.delete(id);
		return new ResponseEntity<Vehicle>(new CustomErrorType("Deleted Vehicle with id " + id + "."),
				HttpStatus.NO_CONTENT);
	}

}

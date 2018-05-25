package com.vehicle.mgmnt.resource;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	public void setvehicleInventoryService(VehicleInventoryService vehicleInventoryService) {
		this.vehicleInventoryService = vehicleInventoryService;
	}

	@GetMapping("/")
	public ResponseEntity<List<Vehicle>> listAllVehicle() {
		logger.info("Fetching all vehicles");
		List<Vehicle> users = vehicleInventoryService.listAllVehicle();
		if (users.isEmpty()) {
			return new ResponseEntity<List<Vehicle>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Vehicle>>(users, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Vehicle> getVehicleById(@PathVariable("id") final Long id) {
		logger.info("Fetching Vehicle with id {}", id);
		Vehicle user = vehicleInventoryService.findById(id);
		if (user == null) {
			logger.error("Vehicle with id {} not found.", id);
			return new ResponseEntity<Vehicle>(new CustomErrorType("Vehicle with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Vehicle>(user, HttpStatus.OK);
	}

	/**
	 * @exception MethodArgumentNotValidException
	 *                (validation fails)
	 */
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vehicle> createVehicle(@Valid @RequestBody final Vehicle vehicle) {
		logger.info("Creating Vehicle : {}", vehicle);
		if (vehicleInventoryService.findByModel(vehicle.getModel()) != null) {
			logger.error("Unable to create. A Vehicle with Model {} already exist", vehicle.getModel());
			return new ResponseEntity<Vehicle>(
					new CustomErrorType(
							"Unable to create new Vehicle. A Vehicle with Model " + vehicle.getModel() + " already exist."),
					HttpStatus.CONFLICT);
		}
		vehicleInventoryService.save(vehicle);
		return new ResponseEntity<Vehicle>(vehicle, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vehicle> updateVehicle(@PathVariable("id") final Long id, @RequestBody Vehicle vehicle) {
		logger.info("Updating vehicle with id {}", id);
		Vehicle currentVehicle = vehicleInventoryService.findById(id);
		if (currentVehicle == null) {
			logger.error("Unable to update. Vehicle with id {} not found.", id);
			return new ResponseEntity<Vehicle>(
					new CustomErrorType("Unable to upate. Vehicle with id " + id + " not found."), HttpStatus.NOT_FOUND);
		}
		currentVehicle.setModel(vehicle.getModel());
		currentVehicle.setYear(vehicle.getYear());
		currentVehicle.setMileage(vehicle.getMileage());
		vehicleInventoryService.saveAndFlush(currentVehicle);
		return new ResponseEntity<Vehicle>(currentVehicle, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Vehicle> deleteUser(@PathVariable("id") final Long id) {
		logger.info("Deleting Vehicle with id {}", id);
		Vehicle user = vehicleInventoryService.findById(id);
		if (user == null) {
			logger.error("Unable to delete. Vehhicle with id {} not found.", id);
			return new ResponseEntity<Vehicle>(
					new CustomErrorType("Unable to delete. Vehicle with id " + id + " not found."), HttpStatus.NOT_FOUND);
		}
		vehicleInventoryService.delete(id);
		return new ResponseEntity<Vehicle>(new CustomErrorType("Deleted Vehicle with id " + id + "."),
				HttpStatus.NO_CONTENT);
	}

}

package com.vehicle.mgmnt.exception;

import com.vehicle.mgmnt.entity.Vehicle;

public class CustomErrorType extends Vehicle {

	private String errorMessage;
	 
    public CustomErrorType(final String errorMessage){
        this.errorMessage = errorMessage;
    }
 
    public String getErrorMessage() {
        return errorMessage;
    }
}

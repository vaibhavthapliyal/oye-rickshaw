package com.oyerickshaw.application.controller;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oyerickshaw.application.request.LocationUpdateRequest;
import com.oyerickshaw.application.response.BaseResponse;
import com.oyerickshaw.application.service.ILocationInsertService;

@RestController
@RequestMapping("/api/insert")
public class LocationController {


	Logger log = LogManager.getLogger(LocationController.class);

	@Autowired
	private ILocationInsertService locationService;
	
	@PutMapping("/location")
	public BaseResponse updateVehicleLocation(@RequestBody LocationUpdateRequest request) throws IOException {
		
		log.info("Received Request to update Location");
		return locationService.insertLocation(request);
	}
}

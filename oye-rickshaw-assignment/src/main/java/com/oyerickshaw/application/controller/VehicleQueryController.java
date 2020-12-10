package com.oyerickshaw.application.controller;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oyerickshaw.application.request.GetNearbyVehiclesRequest;
import com.oyerickshaw.application.response.NearbyVehiclesResponse;
import com.oyerickshaw.application.service.ILocationQueryService;

@RestController
@RequestMapping("/api/query")
public class VehicleQueryController {
	
	@Autowired
	private ILocationQueryService locationQueryService;
	
	Logger log = LogManager.getLogger(VehicleQueryController.class);
	
	@PostMapping("/getVehicles")
	public NearbyVehiclesResponse getVehicles(@RequestBody GetNearbyVehiclesRequest request) throws IOException {
		
		log.info("Received Request to get vehicles: " + request);
		return locationQueryService.getNearbyVehicles(request);
	}
	
}

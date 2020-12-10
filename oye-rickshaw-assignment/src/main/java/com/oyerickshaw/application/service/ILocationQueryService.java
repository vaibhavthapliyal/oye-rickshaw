package com.oyerickshaw.application.service;

import java.io.IOException;

import com.oyerickshaw.application.request.GetNearbyVehiclesRequest;
import com.oyerickshaw.application.response.NearbyVehiclesResponse;

public interface ILocationQueryService {
	NearbyVehiclesResponse getNearbyVehicles(GetNearbyVehiclesRequest request) throws IOException;
}

package com.oyerickshaw.application.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.oyerickshaw.application.constants.Constants;
import com.oyerickshaw.application.dto.Vehicle;
import com.oyerickshaw.application.request.GetNearbyVehiclesRequest;
import com.oyerickshaw.application.response.NearbyVehiclesResponse;
import com.oyerickshaw.application.service.ILocationQueryService;

@Service
public class LocationQueryServiceImpl implements ILocationQueryService {
	
	@Autowired
	@Qualifier("elasticClient")
	private RestHighLevelClient client;
	
	private Gson gson = new Gson(); 
	Logger logger = LogManager.getLogger(LocationQueryServiceImpl.class);

	@Override
	public NearbyVehiclesResponse getNearbyVehicles(GetNearbyVehiclesRequest request) throws IOException {

		SearchSourceBuilder builder = new SearchSourceBuilder();
		
		builder.query(QueryBuilders.geoDistanceQuery(Constants.LOCATION_FIELD)
				.point(request.getLocation().getLat(), request.getLocation().getLon())
				.distance(200, DistanceUnit.KILOMETERS))
				.sort(new GeoDistanceSortBuilder(Constants.LOCATION_FIELD, request.getLocation().getLat(),
						request.getLocation().getLon())).from(0).size(Constants.VEHICLE_RESULT_SIZE_LIMIT);
		
		SearchRequest searchRequest = new SearchRequest(Constants.VEHICLE_LOCATION_TRACKING_INDEX);
		
		searchRequest.source(builder);
		
		logger.info(client);
		
		SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		
		SearchHit [] hits = searchResponse.getHits().getHits();
		
		List<Vehicle> vehicles = new ArrayList<>();
		for (SearchHit searchHit : hits) {
			Vehicle vehicle = gson.fromJson(searchHit.getSourceAsString(), Vehicle.class);
			
			vehicles.add(vehicle);
		}
		NearbyVehiclesResponse response = new NearbyVehiclesResponse("Found " + vehicles.size() + " vehicles within 200 KMS.", true, HttpStatus.OK.value());
		response.setVehicles(vehicles);
		return response;
	}

}

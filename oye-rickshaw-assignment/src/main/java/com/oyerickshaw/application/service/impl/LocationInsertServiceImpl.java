package com.oyerickshaw.application.service.impl;

import java.io.IOException;

import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.oyerickshaw.application.constants.Constants;
import com.oyerickshaw.application.request.LocationUpdateRequest;
import com.oyerickshaw.application.response.BaseResponse;
import com.oyerickshaw.application.service.ILocationInsertService;

@Service
public class LocationInsertServiceImpl implements ILocationInsertService {

	@Autowired
	@Qualifier("elasticClient")
	private RestHighLevelClient client;
	
	private Gson gson = new Gson(); 
	
	@Override
	public BaseResponse insertLocation(LocationUpdateRequest request) throws IOException {
		
		IndexRequest indexRequest = new IndexRequest(Constants.VEHICLE_LOCATION_TRACKING_INDEX).id(request.getVehicleNo()).source(gson.toJson(request), XContentType.JSON);
		
		UpdateRequest updateRequest = new UpdateRequest(Constants.VEHICLE_LOCATION_TRACKING_INDEX, request.getVehicleNo()).upsert(indexRequest);
		
		updateRequest.doc(indexRequest);
		
		UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);
		
		if (updateResponse.getResult() == DocWriteResponse.Result.CREATED) {
		    return new BaseResponse("Vehicle Entry Successfully Created with updated location", true, HttpStatus.OK.value());
		} else if (updateResponse.getResult() == DocWriteResponse.Result.UPDATED) {
		    return new BaseResponse("Location Updated Successfully", true, HttpStatus.OK.value());
		}
		return new BaseResponse("Request Processed Successfully", true, HttpStatus.OK.value());
	}

}

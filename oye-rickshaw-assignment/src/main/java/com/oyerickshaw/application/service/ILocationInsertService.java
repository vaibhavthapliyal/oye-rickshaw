package com.oyerickshaw.application.service;

import java.io.IOException;

import com.oyerickshaw.application.request.LocationUpdateRequest;
import com.oyerickshaw.application.response.BaseResponse;

public interface ILocationInsertService {
	BaseResponse insertLocation(LocationUpdateRequest request) throws IOException;
}

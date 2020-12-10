package com.oyerickshaw.application.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oyerickshaw.application.response.BaseResponse;


@ControllerAdvice
public class ExceptionController {

	Logger logger = LogManager.getLogger(ExceptionController.class);
	
	@ExceptionHandler(Exception.class)
	public @ResponseBody BaseResponse handleException(Exception ex) {
		logger.error("in handleException method", ex);
		BaseResponse response = new BaseResponse("Internal Server Error", false, HttpStatus.INTERNAL_SERVER_ERROR.value());
		return response;
	}
}

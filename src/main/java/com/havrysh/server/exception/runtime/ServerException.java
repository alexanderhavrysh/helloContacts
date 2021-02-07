package com.havrysh.server.exception.runtime;

import com.havrysh.server.exception.ErrorResponse;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class ServerException extends RuntimeException  {
	private final static String ERROR_VALIDATION = "validationFail";
	private final static String ERROR_ACTION = "actionFail";
	private final ErrorResponse response;
	
	public ServerException(Class<?> entityClass, Long entityId, String errorDetail) {
		super(errorDetail);
		response = new ErrorResponse(entityId, entityClass.getSimpleName(), ERROR_VALIDATION, errorDetail);
		log.error(errorDetail);
	}
	
	public ServerException(Class<?> entityClass, Long entityId, String errorDetail, Throwable throwable) {
		super(errorDetail);
		response = new ErrorResponse(entityId, entityClass.getSimpleName(), ERROR_ACTION, errorDetail);
		log.error(errorDetail, throwable);
	}
}
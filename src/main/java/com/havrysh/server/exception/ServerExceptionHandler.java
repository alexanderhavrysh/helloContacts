package com.havrysh.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.havrysh.server.exception.runtime.ServerException;

@ControllerAdvice
public class ServerExceptionHandler {
	@ExceptionHandler(ServerException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse handleException(ServerException e) {
		return e.getResponse();
	}
}
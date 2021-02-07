package com.havrysh.server.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
	private Long entityId;
	private String entity;
	private String errorCode;
	private String errorDetail;
}
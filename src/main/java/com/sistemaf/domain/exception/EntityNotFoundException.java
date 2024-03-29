package com.sistemaf.domain.exception;


public class EntityNotFoundException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String msg) {
		super(msg);
	}
	public EntityNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}

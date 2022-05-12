package com.sistemaf.domain.exception;

public class EntityUsedException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public EntityUsedException(String msg) {
		super(msg);
	}
	public EntityUsedException(String msg, Throwable cause) {
		super(msg, cause);
	}
}

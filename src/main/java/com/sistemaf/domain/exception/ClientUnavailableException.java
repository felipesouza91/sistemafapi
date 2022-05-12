package com.sistemaf.domain.exception;

public class ClientUnavailableException extends BusinessException {



	private static final long serialVersionUID = 1L;

	public ClientUnavailableException(String msg) {
		super(msg);
	}

	public ClientUnavailableException(String msg, Throwable cause) {
		super(msg,cause);
	}

}

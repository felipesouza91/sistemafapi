package com.sistemaf.infrastructure.exception;


public class SistemaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SistemaException(String mensagem) {
		super(mensagem);
	}
	
	public SistemaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
	public SistemaException(Throwable cause) {
		super(cause);
	}
}
package com.sistemaf.domain.exception;

public class UserNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String msg) {
		super(msg);
	}
	
	public UserNotFoundException(Long id) {
		this(String.format("Não existe um cadastro de Usuário com o codigo %d", id));
	}
}

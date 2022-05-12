package com.sistemaf.infrastructure.util.interfaces;

public interface DtoDisassembler<X, Y> {

	public X toDomainObject(Y inputObject);
}

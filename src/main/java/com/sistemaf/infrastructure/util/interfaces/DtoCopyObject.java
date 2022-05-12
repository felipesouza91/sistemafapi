package com.sistemaf.infrastructure.util.interfaces;

public interface DtoCopyObject<X, Y> {
	
	public void copyToDomainObject(X inputObject, Y object);

}

package com.sistemaf.infrastructure.util.interfaces;

import java.util.List;

public interface DtoAssembler<T, X> {

	public X toModel(T object);
	
	public List<X> toCollectionModel(List<T> list);
}

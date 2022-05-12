package com.sistemaf.infrastructure.util.interfaces;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface FilterUtil<T ,X>  {

	public Predicate[] criarRestricoes(T filter, CriteriaBuilder builder, Root<X> root); 
	
}

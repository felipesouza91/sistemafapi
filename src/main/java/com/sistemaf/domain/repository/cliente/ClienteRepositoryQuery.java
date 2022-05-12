package com.sistemaf.domain.repository.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sistemaf.domain.filter.ClienteFilter;
import com.sistemaf.domain.model.Cliente;

public interface ClienteRepositoryQuery {
	
	public Page<Cliente> filtrar(ClienteFilter clienteFilter, Pageable pageable);

}

package com.sistemaf.domain.repository.security.usuario;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sistemaf.domain.filter.UsuarioFilter;
import com.sistemaf.domain.model.Usuario;
import com.sistemaf.domain.projection.UserSimpleModel;

public interface UsuarioRepositoryQuery {

	public Page<Usuario> filtar(UsuarioFilter filter, Pageable page);
	public List<UserSimpleModel> resumo();
}

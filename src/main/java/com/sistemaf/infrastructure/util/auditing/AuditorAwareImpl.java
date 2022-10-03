package com.sistemaf.infrastructure.util.auditing;

import java.util.Optional;

import com.sistemaf.core.security.SistemaFSecurity;
import com.sistemaf.domain.model.Usuario;
import com.sistemaf.domain.repository.security.usuario.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;

@Service
public class AuditorAwareImpl implements AuditorAware<Usuario> {

	@Autowired
	private SistemaFSecurity security;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Optional<Usuario> getCurrentAuditor() {

		return usuarioRepository.findById(security.getUsuarioId());
	}

}

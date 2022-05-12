package com.sistemaf.domain.repository.security.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaf.domain.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryQuery{

	public Optional<Usuario> findByApelido(String apelido);
    
	public Optional<Usuario> findByApelidoAndAtivo(String apelido, Boolean ativo);
}

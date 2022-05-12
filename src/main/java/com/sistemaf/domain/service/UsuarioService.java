package com.sistemaf.domain.service;

import java.util.List;
import java.util.Optional;

import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.repository.security.grupoacesso.GrupoAcessoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.filter.UsuarioFilter;
import com.sistemaf.domain.model.Usuario;
import com.sistemaf.domain.projection.UserSimpleModel;
import com.sistemaf.domain.repository.security.usuario.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private GrupoAcessoRepository grupoAcessoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Page<Usuario> filtro(UsuarioFilter filter, Pageable page) {
		return usuarioRepository.filtar(filter, page);
	}

	public List<UserSimpleModel> resumo() {
		return usuarioRepository.resumo();
	}

	public Usuario salvar(Usuario usuario) {
		this.verifyAccessGroup(usuario.getGrupoAcesso().getId());
		if (this.usuarioRepository.findByApelido(usuario.getApelido()).isPresent()) {
			throw new BusinessException("O apelido já esta em uso");
		}
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		return usuarioRepository.save(usuario);
	}

	public Usuario buscarPorCodigo(Long codigo) {
		return buscarOption(codigo);
	}

	public Usuario atualizar(Long codigo, Usuario usuario) {
		this.verifyAccessGroup(usuario.getGrupoAcesso().getId());
		Usuario usuarioBuscado = buscarOption(codigo);
		BeanUtils.copyProperties(usuario, usuarioBuscado, "id");
		return usuarioRepository.save(usuarioBuscado);
	}

	private Usuario buscarOption(Long codigo) {
		Optional<Usuario> userOptional = usuarioRepository.findById(codigo);
		return userOptional.orElseThrow(()-> new EntityNotFoundException("O usuario solicitado não existe"));
	}

	private void verifyAccessGroup(Long accessGroupId) {
		this.grupoAcessoRepository.findById(accessGroupId).orElseThrow(() -> new BusinessException("O Grupo de Acesso não existe"));
	}

}

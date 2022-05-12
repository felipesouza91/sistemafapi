package com.sistemaf.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.Usuario;
import com.sistemaf.domain.repository.security.usuario.UsuarioRepository;

@Service
public class ProfileService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public void updatePassword(Long id, String oldPassword, String newPassword, String confirmationPassword) {
		Usuario user = this.buscarOption(id);
		if(!passwordEncoder.matches(oldPassword, user.getSenha())) {
			throw new BusinessException("Erro ao alterar a senha, senha atual não confere");
		}
		if(!newPassword.equals(confirmationPassword)) {
			throw new BusinessException("Erro ao alterar a senha nova senha/confirmação invalidas");
		}
		user.setSenha(passwordEncoder.encode(newPassword));
		this.usuarioRepository.save(user);
	}
	
	private Usuario buscarOption(Long codigo) {
		Optional<Usuario> userOptional = usuarioRepository.findById(codigo);
		return userOptional.orElseThrow(()-> new EntityNotFoundException("O usuario solicitado não existe"));
	}
}

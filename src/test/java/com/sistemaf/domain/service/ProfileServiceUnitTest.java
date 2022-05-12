package com.sistemaf.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.Usuario;
import com.sistemaf.domain.repository.security.usuario.UsuarioRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProfileServiceUnitTest {
	
	@Mock
	private UsuarioRepository repository;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	private Usuario user;

	@InjectMocks
	private ProfileService sut;
	
	@Before
	public void setup() {
		user = new Usuario();
		user.setId(1L);
		user.setAtivo(true);
		user.setApelido("any_username");
		user.setNome("User name");
		user.setSenha("encryptPassword");
	}
		
	@Test(expected = EntityNotFoundException.class)
	public void giveUserNotExists_updateUserPassword_fails() {
		sut.updatePassword(1L, "old_assword","new_password", "confirmation_new_password");
	}
	
	@Test(expected = BusinessException.class)
	public void giveOldPasswordIsInvalid_updateUserPassword_fails() {
		when(repository.findById(Mockito.any())).thenReturn(Optional.of(user));
		sut.updatePassword(1L, "invalid_encryptPassword","new_password", "confirmation_new_password");
	}
	
	@Test(expected = BusinessException.class)
	public void giveNewPasswordAndConfirmationIsInvalid_updateUserPassword_fails() {
		when(repository.findById(Mockito.any())).thenReturn(Optional.of(user));
		sut.updatePassword(1L, "encryptPassword","new_password", "invalid_new_password");
	}
	
	@Test
	public void giveOldPasswordAndNewPasswordValid_updateUserPassword_success() {
		when(repository.findById(1L)).thenReturn(Optional.of(user));
		when(passwordEncoder.matches("old_Password", "encryptPassword")).thenReturn(true);
		when(passwordEncoder.encode(Mockito.any())).thenReturn("encrypt_new_password");
		sut.updatePassword(1L, "old_Password","new_password", "new_password");
		assertThat(user.getSenha()).isEqualTo("encrypt_new_password");
	}
}
